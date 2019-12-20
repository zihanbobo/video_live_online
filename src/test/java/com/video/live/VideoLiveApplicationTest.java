package com.video.live;

import com.google.common.collect.Lists;
import com.video.live.common.util.JWTUtils;
import com.video.live.dao.RoleDao;
import com.video.live.dao.UserDao;
import com.video.live.dao.UserRoleDao;
import com.video.live.entity.Role;
import com.video.live.entity.User;
import com.video.live.entity.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Test
    public void addUser(){
        User user=new User();
        user.setAge(15);
        user.setSex("男");
        user.setPassword(passwordEncoder.encode("root@123"));
        user.setUserName("root");
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
