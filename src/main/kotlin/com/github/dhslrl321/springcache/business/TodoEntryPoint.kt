package com.github.dhslrl321.springcache.business

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TodoEntryPoint(
    private val repository: TodoRepository,
    private val service: TodoService,
) {
    @PostMapping("/todos")
    fun create(@RequestBody body: TodoCreateHttpRequest): ResponseEntity<Todo> {
        return ResponseEntity.ok(service.create(body.userId, body.name))
    }

    @GetMapping("/users/{userId}/todos")
    fun findAll(@PathVariable userId: Long): ResponseEntity<List<Todo>> {
        return ResponseEntity.ok(service.findAllBy(userId))
    }
}

data class TodoCreateHttpRequest(
    val userId: Long,
    val name: String,
)
