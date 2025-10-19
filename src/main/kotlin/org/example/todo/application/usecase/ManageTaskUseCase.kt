package org.example.todo.application.usecase

import org.example.todo.application.dto.TaskDto
import org.example.todo.domain.model.Task
import org.example.todo.domain.repository.TaskRepository
import org.example.todo.domain.service.TaskDomainService
import org.springframework.stereotype.Service

@Service
class ManageTaskUseCase(
    private val taskRepository: TaskRepository,
    private val domainService: TaskDomainService
) {

    fun createTask(dto: TaskDto): Task {
        val task = Task(
            title = dto.title,
            dueDate = dto.dueDate,
            priority = dto.priority,
            category = dto.category
        )
        domainService.validate(task)
        return taskRepository.save(task)
    }

    fun getTasks(): List<Task> = taskRepository.findAll()

    fun updateTask(id: Long, dto: TaskDto): Task {
        val existing = taskRepository.findById(id)
            ?: throw IllegalArgumentException("Task not found")
        val updated = existing.copy(
            title = dto.title,
            dueDate = dto.dueDate,
            priority = dto.priority,
            category = dto.category,
            isCompleted = dto.isCompleted
        )
        domainService.validate(updated)
        return taskRepository.save(updated)
    }

    fun completeTask(id: Long): Task {
        val existing = taskRepository.findById(id)
            ?: throw IllegalArgumentException("Task not found")
        return taskRepository.save(existing.complete())
    }

    fun deleteTask(id: Long) = taskRepository.deleteById(id)
}
