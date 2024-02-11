package com.github.dhslrl321.springcache.business

import jakarta.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Caching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.stereotype.Service

@Service
class TodoCommandService(
    private val repository: TodoRepository,
) {
    /**
     * userId 에 todo 하나를 생성합니다
     *
     * todo 가 생성되었으니 userId 로 연결된 cache 는 invalidate 되어야 합니다
     *
     * checkPoint 1. CacheEvict 를 하지 않으면 어떻게 될까?
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
     * 또한 userId 로 연결된 cache 또한 update 해야합니다
     *
     * checkPoint 1. CachePut 을 하지 않으면 어떻게 될까?
     * checkPoint 2. CachePut 을 하지 않고 Evict 만 하면?
     * checkPoint 3. evict = [CacheEvict(cacheNames = ["todosByUserId"])] 라고만 명시하면?
     *      - 전체 유저 캐시가 지워짐
     *      - 하지만 allEntities 속성 안주면 안지워짐
     * checkPoint 4. userId cache 는 어떻게 evict 시키지?
     *      - 해결 1. expiration 을 이용한다
     *      - 해결 2. param 으로 userId 를 받는다
     *      - 해결 3. cacheManager 를 직접 호출
     */
    @Caching(
        put = [CachePut(cacheNames = ["todoById"], key = "#todoId")],
        evict = [CacheEvict(cacheNames = ["todosByUserId"], allEntries = true)]
    )
    fun transit(todoId: Long, status: String): Todo {

        val todo = repository.findById(todoId).orElseThrow()
        todo.transitTo(TodoStatus.valueOf(status))

        return repository.save(todo)
    }
}
