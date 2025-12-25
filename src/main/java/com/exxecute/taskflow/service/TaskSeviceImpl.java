package com.exxecute.taskflow.service;

import com.exxecute.taskflow.exception.NotFoundException;
import com.exxecute.taskflow.model.entity.Task;
import com.exxecute.taskflow.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSeviceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskSeviceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(Long id, Task task) {
        Task existingTask = getById(id);
        BeanUtils.copyProperties(task, existingTask, "id"); /* TODO: maybe create task mapper for this */
        return taskRepository.save(existingTask);
    }

    @Override
    public void delete(Long id) {
        Task existingTask = getById(id);
        taskRepository.delete(existingTask);
    }
}
