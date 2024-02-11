package com.github.dhslrl321.springcache.business

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class TodoCommandService(
    private val repository: TodoRepository,
) {
    /**
     * userId 에 todo 하나를 생성합니다
     *
     * todo 가 생성되었으니 userId 로 연결된 cache 는 invalidate 되어야 합니다
     */
    @CacheEvict(cacheNames = ["todosByUserId"], key = "#userId")
    fun create(userId: Long, name: String): Todo {
        val todo = Todo(
            userId = userId,
            name = name,
        )
        return repository.save(todo)
    }

    /**
     * todo 의 상태를 변경합니다
     *
     * todo 의 상태가 변경되었으므로 todoId 로 연결된 cache 는 update 되어야 합니다
     */
    @CachePut(cacheNames = ["todoById"], key = "#todoId")
    fun transit(todoId: Long, status: String): Todo {

        val todo = repository.findById(todoId).orElseThrow()

        val newStatus = when (status) {
            "IN_PROGRESS" -> TodoStatus.IN_PROGRESS
            "DONE" -> TodoStatus.DONE
            else -> throw IllegalArgumentException("Todo의 Status($status)는 존재하지 않습니다")
        }

        todo.transitTo(newStatus)

        return repository.save(todo)
    }
}
