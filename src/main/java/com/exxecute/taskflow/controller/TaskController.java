package com.exxecute.taskflow.controller;
import com.exxecute.taskflow.exception.found.TaskNotFoundException;
import com.exxecute.taskflow.logging.AppLogger;
import com.exxecute.taskflow.logging.LoggerFactory;
import com.exxecute.taskflow.model.dto.TaskDto;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.service.TaskService;
import com.exxecute.taskflow.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import com.exxecute.taskflow.model.dto.TaskDto;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final AppLogger log = LoggerFactory.getLogger(TaskController.class);


    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskDto taskCreate) {
        log.info("TaskController:createTask");
        return taskService.create(taskCreate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskByID(@PathVariable Long id) {
        log.info("TaskController:getByID");
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskUpdate) {
        log.info("TaskController:updateTask, id: " + id);
        return taskService.update(id, taskUpdate);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        log.info("TaskController:deleteTask, id: " + id);
        taskService.delete(id);
    }
}
