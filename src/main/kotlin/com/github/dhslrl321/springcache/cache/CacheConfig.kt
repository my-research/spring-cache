package com.github.dhslrl321.springcache.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class CacheConfig {
    @Bean
    fun caffeineCacheManager(caffeine: Caffeine<Any, Any>): CacheManager {
        val manager = CaffeineCacheManager()
        manager.setCaffeine(caffeine)
        return manager
    }

    @Bean
    fun caffeine(): Caffeine<Any, Any>? {
        return Caffeine.newBuilder()
            .initialCapacity(10)
            .expireAfterWrite(Duration.of(100, ChronoUnit.SECONDS))
            .recordStats()
    }
}
