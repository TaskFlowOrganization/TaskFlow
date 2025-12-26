package com.exxecute.taskflow.service;

import com.exxecute.taskflow.model.entity.Task;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

/**
 * Task service interface.
 *
 * @author Uladzislau Mikhayevich
 */
public interface TaskService {

    /**
     * Create new Task.
     * @param task New Task to create.
     * @return New created Task.
     */
    Task create(Task task);

    /**
     * Get Task by ID.
     * @param id Task ID.
     * @return Task found by ID.
     * @throws ChangeSetPersister.NotFoundException if not found a Task with this ID.
     */
    Task getById(Long id) throws ChangeSetPersister.NotFoundException;

    /**
     * Get All Tasks.
     * @return List of all Tasks.
     */
    List<Task> getAll();

    /**
     * Put new data to created Task.
     * @param id ID of the Task to edit.
     * @param task Data to edit Task.
     * @return New Task.
     * @throws ChangeSetPersister.NotFoundException if not found a Task with this ID.
     */
    Task update(Long id, Task task) throws ChangeSetPersister.NotFoundException;

    /**
     * Delete Task by ID.
     * @param id ID of the Task to delete.
     * @throws ChangeSetPersister.NotFoundException if not found a Task with this ID.
     */
    void delete(Long id) throws ChangeSetPersister.NotFoundException;
}
