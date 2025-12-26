package com.exxecute.taskflow.service;

import com.exxecute.taskflow.model.entity.Task;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TaskService {

    Task create(Task task);

    Task getById(Long id) throws ChangeSetPersister.NotFoundException;

    List<Task> getAll();

    Task update(Long id, Task task) throws ChangeSetPersister.NotFoundException;

    void delete(Long id) throws ChangeSetPersister.NotFoundException;
}
