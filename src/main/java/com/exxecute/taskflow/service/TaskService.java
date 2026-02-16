package com.exxecute.taskflow.service;

import com.exxecute.taskflow.model.dto.TaskDto;
import com.exxecute.taskflow.model.entity.Task;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Task service interface.
 *
 * @author Uladzislau Mikhayevich
 */
public interface TaskService {

    /**
     * Create new Task.
     * @param taskDto New Task to create.
     * @return New created Task.
     */
    Task create(TaskDto taskDto);

    /**
     * Get Task by ID.
     * @param id Task ID.
     * @return Task found by ID.
     */
    Task getById(Long id);

    /**
     * Get All Tasks.
     * @return List of all Tasks.
     */
    List<Task> getAll();

    /**
     * Put new data to created Task.
     * @param id ID of the Task to edit.
     * @param taskDto Data to edit Task.
     * @return New Task.
     */
    Task update(Long id, TaskDto taskDto);

    /**
     * Delete Task by ID.
     * @param id ID of the Task to delete.
     */
    void delete(Long id);
}
