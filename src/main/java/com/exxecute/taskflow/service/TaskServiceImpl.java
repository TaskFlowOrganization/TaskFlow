package com.exxecute.taskflow.service;

import com.exxecute.taskflow.exception.NotFoundException;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
        this.taskRepository = taskRepository;
    }

    /**
     * Create new Task.
     * @param task New Task to create.
     * @return New created Task.
     */
    @Override
    public Task create(final Task task) {
        return taskRepository.save(task);
    }

    /**
     * Get Task by ID.
     * @param id Task ID.
     * @return Task found by ID.
     * @throws NotFoundException if not found a Task with this ID.
     */
    @Override
    public Task getById(final Long id) throws NotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found " + " task")); /* TODO: custom exception not this */
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
     * @param task Data to edit Task.
     * @return New Task.
     * @throws NotFoundException if not found a Task with this ID.
     */
    @Override
    public Task update(final Long id, final Task task) throws NotFoundException {
        Task existingTask = getById(id);
        BeanUtils.copyProperties(task, existingTask, Task.ID_NAME); /* TODO: maybe create task mapper for this */
        return taskRepository.save(existingTask);
    }

    /**
     * Delete Task by ID.
     * @param id ID of the Task to delete.
     * @throws NotFoundException if not found a Task with this ID.
     */
    @Override
    public void delete(final Long id) throws NotFoundException {
        Task existingTask = getById(id);
        taskRepository.delete(existingTask);
    }
}
