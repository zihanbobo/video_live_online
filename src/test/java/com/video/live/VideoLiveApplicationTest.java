package com.video.live;

import com.google.common.collect.Lists;
import com.video.live.common.util.JWTUtils;
import com.video.live.dao.RoleDao;
import com.video.live.dao.UserDao;
import com.video.live.dao.UserRoleDao;
import com.video.live.entity.User;
import com.video.live.entity.UserRole;
import com.video.live.job.TaskJobSupport;
import com.video.live.job.impl.TestJobImpl;
import com.video.live.web.controller.VideoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoLiveApplicationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoController videoController;

    @Autowired
    private TaskJobSupport jobSupport;

    @Test
    public void testQuartz() throws InterruptedException {
        Map<String,String> param=new HashMap<>();
        param.put("name","-任务1-");
        jobSupport.addJob(TestJobImpl.class,"0/1 * * * * ? * " ,"任务名1","任务组1",param);
        param.put("name","-任务2-");
        jobSupport.addJob(TestJobImpl.class,"0/1 * * * * ? * " ,"任务名2","任务组2",param);
        Thread.sleep(60000);
    }

    @Test
    public void schedule(){
        videoController.testSche("name");
    }

    @Test
    public void redis(){
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        List<User> userList=Lists.newArrayList();

        for (int i=0;i<5;i++){
            User user=new User();
            user.setId(Long.valueOf(i));
            user.setUserName("测试"+i);
            user.setPassword("password"+i);
            user.setAge(15);
            user.setPhone("123455"+i);
           // userList.add(user);
            redisTemplate.opsForSet().add("userSet",user);
        }
        Set userSet = redisTemplate.opsForSet().members("userSet");
        System.out.println(userSet.toString());
        redisTemplate.opsForHash().put("testsd","test",123);
        redisTemplate.opsForValue().set("testadf","这是一个测试");
        redisTemplate.opsForValue().set("数字ad",234234);

    }

    @Test
    public void addUser(){
        User user=new User();
        user.setAge(15);
        user.setSex("男");
        user.setPassword(passwordEncoder.encode("root@123"));
        user.setUserName("dyh");
        user.setPhone("13227805078");
        Long id = userDao.save(user).getId();
        UserRole userRole=new UserRole();
        userRole.setUserId(id);
        userRole.setRoleId(1L);
        UserRole save = userRoleDao.save(userRole);
    }

    @Test
    public void jwtTest(){
        String token = JWTUtils.generate("dyh");
        System.out.println(token);

        String userName = JWTUtils.getUserName(token);
        System.out.println(userName);
    }

    @Test
    public void contextLoads() {
        String pictureURL = new String("http://wprd01.is.autonavi.com/appmaptile?x=6&y=3&z=4&lang=zh_cn&size=1&scl=1&style=7".getBytes(),StandardCharsets.UTF_8);
        String storagePath = "D:\\test.png";
        try {

            System.out.println(pictureURL);
            HttpHeaders httpHeaders = new HttpHeaders();
            ArrayList<MediaType> mediaTypes = Lists.newArrayList(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setAccept(mediaTypes);
            HttpEntity<byte[]> httpEntity = new HttpEntity<>(httpHeaders);
            ResponseEntity<byte[]> exchange = restTemplate.exchange(pictureURL, HttpMethod.GET, httpEntity, byte[].class);
            HttpStatus httpStatus = exchange.getStatusCode();
            if (httpStatus.is2xxSuccessful()) {
                FileCopyUtils.copy(exchange.getBody(), new File(storagePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
