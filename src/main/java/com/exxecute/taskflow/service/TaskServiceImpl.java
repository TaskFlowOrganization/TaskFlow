package com.exxecute.taskflow.service;

import com.exxecute.taskflow.exception.found.NotFoundException;
import com.exxecute.taskflow.exception.found.TaskNotFoundException;
import com.exxecute.taskflow.model.dto.TaskDto;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.model.entity.User;
import com.exxecute.taskflow.repository.JpaUserRepository;
import com.exxecute.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of task service.
 *
 * @author Uladzislau Mikhayevich
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    /**
     * Task Repository.
     */
    private final TaskRepository taskRepository;
    private final JpaUserRepository userRepository;

    /**
     * Assigning Task to User.
     * Entities should exist in db.
     * @param taskId Task id entity.
     * @param userId User id entity.
     */
    @Override
    @Transactional
    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));

        task.setUser(user);
    }

    /**
     * Get Tasks By User.
     * @param userId User id.
     * @return List of the tasks that assigned to user.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByUser(Long userId) {
        return this.taskRepository.findByUserId(userId);
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

        Objects.requireNonNull(taskDto, "Task must not be null");

        BeanUtils.copyProperties(taskDto, task);
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
