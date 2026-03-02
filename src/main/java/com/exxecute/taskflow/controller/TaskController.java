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
    public Task createTask(@Valid@RequestBody TaskDto taskCreate) {
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
    public Task updateTask(@PathVariable Long id,
                           @Valid @RequestBody TaskDto taskUpdate) {
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

    /**
     * PUT /tasks/{taskId}/assign/{userId}
     * Assigning Task to User.
     * @param taskId Task id entity.
     * @param userId User id entity.
     */
    @PutMapping("/{taskId}/assign/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignTaskToUser(@PathVariable Long taskId,
                                 @PathVariable Long userId) {
        log.info(String.format("TaskController:assignTaskToUser, taskId=%d, userId=%d", taskId, userId));
        taskService.assignTaskToUser(taskId, userId);
    }

    /**
     * GET /tasks/user/{userId}
     * Get Tasks By User.
     * @param userId User id.
     * @return List of the tasks that assigned to user.
     */
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        log.info(String.format("TaskController:getTasksByUser, userId=%d", userId));
        return taskService.getTasksByUser(userId);
    }
}
