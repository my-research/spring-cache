package com.github.dhslrl321.springcache.business

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Todo private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var userId: Long,
    var name: String,
    var status: TodoStatus
) {

    constructor(userId: Long, name: String) : this(null, userId, name, TodoStatus.TODO)

    fun transitTo(newStatus: TodoStatus) {
        this.status = newStatus
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Todo) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

enum class TodoStatus {
    TODO, IN_PROGRESS, DONE
}
