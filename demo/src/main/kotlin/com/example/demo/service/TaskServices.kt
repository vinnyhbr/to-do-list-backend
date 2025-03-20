package com.example.demo.service

import com.example.demo.model.Task
import com.example.demo.repository.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskService(private val repository: TaskRepository) {
    fun getAllTasks(): List<Task> = repository.findAll()

    fun createTask(task: Task): Task = repository.save(task)

    fun getTaskById(id: Long): Task = repository.findById(id).orElseThrow { RuntimeException("Task not found") }

    @Transactional
    fun updateTask(id: Long, updatedTask: Task): Task {
        return repository.findById(id).map {
            val task = it.copy(title = updatedTask.title, description = updatedTask.description, completed = updatedTask.completed)
            repository.save(task)
        }.orElseThrow { RuntimeException("Task not found") }
    }

    fun deleteTask(id: Long) {
        repository.deleteById(id)
    }
}