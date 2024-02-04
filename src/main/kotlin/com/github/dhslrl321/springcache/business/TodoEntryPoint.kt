package com.github.dhslrl321.springcache.business

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TodoEntryPoint(
    private val repository: TodoRepository,
    private val queryService: TodoQueryService,
) {
    @PostMapping("/todos")
    fun create(@RequestBody body: TodoCreateHttpRequest): ResponseEntity<Todo> {
        val todo = Todo(
            userId = body.userId,
            name = body.name
        )
        return ResponseEntity.ok(repository.save(todo))
    }

    @GetMapping("/users/{userId}/todos")
    fun findAll(@PathVariable userId: Long): ResponseEntity<List<Todo>> {
        return ResponseEntity.ok(queryService.findAllBy(userId))
    }
}

data class TodoCreateHttpRequest(
    val userId: Long,
    val name: String,
)
