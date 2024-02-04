package com.github.dhslrl321.springcache.business

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TodoRepositoryTest @Autowired constructor(
    private val sut: TodoRepository
) {
    @Test
    fun functionalityTest() {
        val actual = sut.save(Todo(userId = 99, name = "hello"))

        sut.findById(actual.id!!).orElseThrow() shouldBe actual
    }

    @Test
    fun byUserId() {
        sut.save(Todo(userId = 999, name = "hello"))

        sut.findAllByUserId(999).size shouldBe 1
        sut.findAllByUserId(1).size shouldBe 0
    }
}
