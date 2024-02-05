package com.github.dhslrl321.springcache.business

import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val repository: TodoRepository,
) {
    @Cacheable("todos")
    fun findAllBy(id: Long): List<Todo> {
        val toList = repository.findAllByUserId(id).toList()
        return toList
    }

    @CachePut(cacheNames = ["todos"], key = "#userId")
    fun create(userId: Long, name: String): Todo {
        val todo = Todo(
            userId = userId,
            name = name
        )
        return repository.save(todo)
    }
}
