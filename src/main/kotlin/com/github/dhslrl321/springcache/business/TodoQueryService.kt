package com.github.dhslrl321.springcache.business

import com.github.dhslrl321.springcache.support.SleepUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TodoQueryService(
    private val repository: TodoRepository,
) {
    /**
     * userId 에 생성된 모든 todo 를 조회합니다
     *
     * 조회와 동시에 userId 에 todo 들을 cache 에 저장합니다
     */
    @Cacheable(cacheNames = ["todosByUserId"])
    fun findAllBy(userId: Long): List<Todo> {
        SleepUtils.sleep()
        return repository.findAllByUserId(userId).toList()
    }

    /**
     * todoId 로 todo 를 조회합니다
     *
     * 조회와 동시에 todoId 에 todo 들을 cache 에 저장합니다
     */
    @Cacheable("todoById")
    fun findBy(id: Long): Todo {
        SleepUtils.sleep()
        return repository.findById(id).orElseThrow()
    }
}
