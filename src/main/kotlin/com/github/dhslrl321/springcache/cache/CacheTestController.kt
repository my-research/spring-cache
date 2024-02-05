package com.github.dhslrl321.springcache.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CacheTestController(
    private val service: MySimpleCacheService,
    private val cacheManager: CacheManager
) {
    @GetMapping("/tests/cache/{id}")
    fun check(@PathVariable id: String): ResponseEntity<Map<String, String>> {
        service.doNoting("a")
        return ResponseEntity.ok(mapOf("message" to service.get(id)))
    }
}
