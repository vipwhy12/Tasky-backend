package org.example.todo.infrastructure.persistence

import org.example.todo.domain.model.Task
import org.example.todo.domain.repository.TaskRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Repository
class TaskRepositoryImpl : TaskRepository {
    
    private val tasks = ConcurrentHashMap<Long, Task>()
    private val idGenerator = AtomicLong(1L)

    override fun save(task: Task): Task {
        val taskId = task.id ?: idGenerator.getAndIncrement()
        val savedTask = task.copy(id = taskId, updatedAt = java.time.LocalDateTime.now())
        tasks[taskId] = savedTask
        return savedTask
    }

    override fun findAll(): List<Task> = tasks.values.toList()

    override fun findById(id: Long): Task? = tasks[id]

    override fun deleteById(id: Long) {
        tasks.remove(id)
    }
}
