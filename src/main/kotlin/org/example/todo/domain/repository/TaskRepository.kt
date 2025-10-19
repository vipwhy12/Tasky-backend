package org.example.todo.domain.repository

import org.example.todo.domain.model.Task

interface TaskRepository {
    fun save(task: Task): Task
    fun findAll(): List<Task>
    fun findById(id: Long): Task?
    fun deleteById(id: Long)
}
