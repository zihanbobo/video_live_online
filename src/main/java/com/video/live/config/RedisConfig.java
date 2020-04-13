package com.video.live.config;

import com.video.live.common.constant.TopicEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.video.live.common.constant.CacheConstant.DEFAULT_EXPIRE_10;
import static com.video.live.common.constant.CacheConstant.USER_NAME_CACHE;
import static com.video.live.common.constant.CacheConstant.USER_NAME_EXPIRE_23;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;


/**
 * redis缓存配置
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/26 10:30
 */
@Configuration
@EnableCaching
@CacheConfig
@SuppressWarnings("unchecked")
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory),
                this.cacheConfigEntryTtl(DEFAULT_EXPIRE_10, ChronoUnit.SECONDS),
                this.redisCacheConfigMap());
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(this.serializer());
        return redisTemplate;
    }

    /**
     * 注入Redis消息监听容器，实现消息的发布和订阅
     *
     * @param factory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory factory, MessageListener listenerAdapter) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(factory);
        listenerContainer.addMessageListener(listenerAdapter, listTopic());
        return listenerContainer;
    }

    private List<PatternTopic> listTopic() {
        return TopicEnum.topicList.stream()
                .map(topic -> new PatternTopic(topic))
                .collect(Collectors.toList());
    }

    private Map<String, RedisCacheConfiguration> redisCacheConfigMap() {
        // 不同的缓存设置对应的失效时间
        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        cacheConfigurationMap.put(USER_NAME_CACHE, cacheConfigEntryTtl(USER_NAME_EXPIRE_23, ChronoUnit.HOURS));
        return cacheConfigurationMap;
    }

    private RedisCacheConfiguration cacheConfigEntryTtl(long time, TemporalUnit timeUnit) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(fromSerializer(this.serializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.of(time, timeUnit));
    }

    //使用FastJson代替JdkSerializer
    private RedisSerializer serializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
