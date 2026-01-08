package com.exxecute.taskflow.controller;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.create(task);
    }


}
