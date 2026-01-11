package com.exxecute.taskflow.controller;
import com.exxecute.taskflow.logging.AppLogger;
import com.exxecute.taskflow.logging.LoggerFactory;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Task createTask(@RequestBody Task taskCreate) {
        log.info("TaskController:createTask");
        return taskService.create(taskCreate);
    }

    @GetMapping("/{id}")
    public Task getByID(@PathVariable Long id) {
        log.info("TaskController:getByID");
        return taskService.getById(id);
    }


}
