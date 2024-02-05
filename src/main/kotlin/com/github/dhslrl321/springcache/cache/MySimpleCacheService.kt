package com.github.dhslrl321.springcache.cache

import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MySimpleCacheService {
    @Cacheable("simpleGet")
    fun get(): String {
        return "hello world"
    }

    @Cacheable(
        cacheNames = ["conditionalCache"],
        condition = "#id.length() > 10", // id 가 10 글자 이상 일 때만 cache 를 타도록
    )
    fun get(id: String): String {
        return "hello world $id"
    }

    @Cacheable(
        cacheNames = ["conditionalCache"],
    )
    fun doNoting(id: String) {
        println("hello")
    }
}
