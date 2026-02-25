package com.exxecute.taskflow.repository;

import com.exxecute.taskflow.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  {

    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findById(Long id);

    void delete(Task task);

    boolean existsById(Long id);

    List<Task> findByTitle(String title);

    List<Task> findByUserId(Long userId);
}
