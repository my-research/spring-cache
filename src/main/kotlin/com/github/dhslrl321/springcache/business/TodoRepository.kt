package com.github.dhslrl321.springcache.business

import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<Todo, Long> {
    fun findAllByUserId(userId: Long): List<Todo>
}
