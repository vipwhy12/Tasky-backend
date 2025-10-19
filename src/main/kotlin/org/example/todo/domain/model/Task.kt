package org.example.todo.domain.model

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val title: String,
    val dueDate: LocalDateTime? = null,
    val priority: String? = null,
    val category: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun complete(): Task = this.copy(isCompleted = true)
    fun reschedule(newDate: LocalDateTime): Task = this.copy(dueDate = newDate)
}
