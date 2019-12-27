package com.video.live.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

import static com.video.live.common.constant.CacheConstant.USER_NAME_CACHE;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;


/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/26 10:30
 */
@Configuration
@EnableCaching
@CacheConfig
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory),
                this.getCacheConfigOfTTL(10, ChronoUnit.SECONDS),
                this.getRedisCacheConfigMap());
    }

    @Bean
    @ConditionalOnMissingBean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(this.getSerializer());
        return redisTemplate;
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigMap() {
        // 不同的缓存设置对应的失效时间
        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        cacheConfigurationMap.put(USER_NAME_CACHE, getCacheConfigOfTTL(23, ChronoUnit.HOURS));
        return cacheConfigurationMap;
    }

    private RedisCacheConfiguration getCacheConfigOfTTL(long time, TemporalUnit timeUnit) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(fromSerializer(this.getSerializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.of(time, timeUnit));
    }

    //使用FastJson代替JdkSerializer
    private RedisSerializer getSerializer() {
        return new FastJsonRedisSerializer(Object.class);
    }
}
