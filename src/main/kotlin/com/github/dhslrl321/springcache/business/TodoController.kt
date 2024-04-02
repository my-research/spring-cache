package com.github.dhslrl321.springcache.business

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(
    private val commandService: TodoCommandService,
    private val queryService: TodoQueryService,
) {
    @PostMapping("/todos")
    fun create(@RequestBody body: CreateTodoHttpRequest): ResponseEntity<Todo> {
        return ResponseEntity.ok(commandService.create(body.userId, body.name))
    }

    @GetMapping("/users/{userId}/todos")
    fun findAll(@PathVariable userId: Long): ResponseEntity<List<Todo>> {
        return ResponseEntity.ok(queryService.findAllBy(userId))
    }

    @GetMapping("/todos/{todoId}")
    fun findBy(@PathVariable todoId: Long): ResponseEntity<Todo> {
        return ResponseEntity.ok(queryService.findBy(todoId))
    }

    @PutMapping("/todos/{todoId}")
    fun update(@PathVariable todoId: Long, @RequestBody body: UpdateTodoStatusHttpRequest): ResponseEntity<Void> {
        commandService.transit(todoId, body.status)
        return ResponseEntity.ok().build()
    }
}

data class CreateTodoHttpRequest(
    val userId: Long,
    val name: String,
)

data class UpdateTodoStatusHttpRequest(
    val status: String,
)
