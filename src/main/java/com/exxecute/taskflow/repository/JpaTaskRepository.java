package com.exxecute.taskflow.repository;

import com.exxecute.taskflow.model.entity.Task;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface JpaTaskRepository extends JpaRepository<Task, Long>, TaskRepository {

}

