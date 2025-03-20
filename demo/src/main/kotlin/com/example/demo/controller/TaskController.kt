package com.example.demo.controller

import com.example.demo.model.Task
import com.example.demo.service.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val service: TaskService) {

    @GetMapping
    fun getAllTasks(): List<Task> = service.getAllTasks()

    @PostMapping
    fun createTask(@RequestBody task: Task): Task = service.createTask(task)

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): Task = service.getTaskById(id)

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody updatedTask: Task): Task = service.updateTask(id, updatedTask)

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        service.deleteTask(id)
    }
}