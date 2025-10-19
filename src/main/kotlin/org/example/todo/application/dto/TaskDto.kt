package org.example.todo.application.dto

import java.time.LocalDateTime

data class TaskDto(
    val id: Long? = null,
    val title: String,
    val dueDate: LocalDateTime? = null,
    val priority: String? = null,
    val category: String? = null,
    val isCompleted: Boolean = false
)
