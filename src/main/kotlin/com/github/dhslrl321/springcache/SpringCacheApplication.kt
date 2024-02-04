package com.github.dhslrl321.springcache

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class SpringCacheApplication

fun main(args: Array<String>) {
	runApplication<SpringCacheApplication>(*args)
}
