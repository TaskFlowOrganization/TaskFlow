package com.exxecute.taskflow.repository;

import com.exxecute.taskflow.model.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TestTaskRepository implements TaskRepository {

    public TestTaskRepository() {

    }
    
    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public List<Task> findByTitle(String title) {
        return List.of();
    }
}
