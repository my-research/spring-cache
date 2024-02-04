package com.github.dhslrl321.springcache.business

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TodoTest {
    @Test
    fun entityTest() {
        val todo1 = Todo(1, userId = 99, "jang")
        val todo2 = Todo(1, userId = 99, "kim")

        todo1 shouldBe todo2
        todo1.name shouldNotBe todo2.name
    }
}
