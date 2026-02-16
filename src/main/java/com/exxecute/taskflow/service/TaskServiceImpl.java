package com.exxecute.taskflow.service;

import com.exxecute.taskflow.exception.found.TaskNotFoundException;
import com.exxecute.taskflow.model.dto.TaskDto;
import com.exxecute.taskflow.model.entity.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of task service.
 *
 * @author Uladzislau Mikhayevich
 */
@Service
public class TaskServiceImpl implements TaskService {
    /**
     * Task Repository.
     */
    private final TaskRepository taskRepository;

    /**
     * Constructor.
     *
     * @param taskRepository Uses Task Repository.
     */
    public TaskServiceImpl(final TaskRepository taskRepository) {
        Objects.requireNonNull(taskRepository, "Task repository must not be null");

        this.taskRepository = taskRepository;
    }

    /**
     * Create new Task.
     * @param taskDto New Task to create.
     * @return New created Task.
     */
    @Override
    public Task create(final TaskDto taskDto) {
        Objects.requireNonNull(taskDto, "Task must not be null");

        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task, Task.ID_NAME); /* TODO: maybe create task mapper for this */

        return taskRepository.save(task);
    }

    /**
     * Get Task by ID.
     * @param id Task ID.
     * @return Task found by ID.
     * @throws TaskNotFoundException if not found a Task with this ID.
     */
    @Override
    public Task getById(final Long id) throws TaskNotFoundException {
        Objects.requireNonNull(id, "Task id must not be null");

        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Get All Tasks.
     * @return List of all Tasks.
     */
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Put new data to created Task.
     * @param id ID of the Task to edit.
     * @param taskDto Data to edit Task.
     * @return New Task.
     * @throws TaskNotFoundException if not found a Task with this ID.
     */
    @Override
    public Task update(final Long id, final TaskDto taskDto) throws TaskNotFoundException {
        Objects.requireNonNull(id, "Task id must not be null");
        Objects.requireNonNull(taskDto, "Task must not be null");

        Task existingTask = getById(id);
        BeanUtils.copyProperties(taskDto, existingTask, Task.ID_NAME); /* TODO: maybe create task mapper for this */
        return taskRepository.save(existingTask);
    }

    /**
     * Delete Task by ID.
     * @param id ID of the Task to delete.
     * @throws TaskNotFoundException if not found a Task with this ID.
     */
    @Override
    public void delete(final Long id) throws TaskNotFoundException {
        Objects.requireNonNull(id, "Task id must not be null");

        Task existingTask = getById(id);
        taskRepository.delete(existingTask);
    }
}
