package com.luv2shopdev.ecommerce.redis;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class CacheConfig {



 @Bean
public RedisCacheConfiguration cacheConfiguration() {
   return RedisCacheConfiguration.defaultCacheConfig()
    .entryTtl(Duration.ofMinutes(60))
    .disableCachingNullValues()
    .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
}



@Bean
public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
   return (builder) -> builder
           .withCacheConfiguration("mailCache",
                     RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
             .withCacheConfiguration("customerCache",
                     RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
 }


}