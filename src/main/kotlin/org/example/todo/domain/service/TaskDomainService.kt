package org.example.todo.domain.service

import org.example.todo.domain.model.Task
import org.springframework.stereotype.Service

@Service
class TaskDomainService {
    fun validate(task: Task) {
        require(task.title.isNotBlank()) { "Task title cannot be empty" }
    }
}
