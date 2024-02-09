package com.github.dhslrl321.springcache.cache

import org.springframework.boot.autoconfigure.cache.CacheProperties.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfig {
    @Bean
    fun caffeineCacheManager(): CacheManager {
        return CaffeineCacheManager()
    }
}
