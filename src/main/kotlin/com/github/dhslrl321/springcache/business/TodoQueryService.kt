package com.github.dhslrl321.springcache.business

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TodoQueryService(
    private val repository: TodoRepository,
) {
    @Cacheable("todos")
    fun findAllBy(id: Long): List<Todo> {
        return repository.findAllByUserId(id).toList()
    }
}
