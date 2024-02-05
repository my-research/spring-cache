package com.github.dhslrl321.springcache.cache

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager
import org.springframework.cache.get


@SpringBootTest
class MySimpleCacheServiceTest @Autowired constructor(
    private val sut: MySimpleCacheService,
    private val cacheManager: CacheManager,
) {
    @Test
    fun name() {
        sut.get("god1")
    }

    companion object {
        private const val CACHE_NAME = "conditionalCache"
    }
}
