package com.example.tasker.repository;

import org.springframework.stereotype.Repository;
import com.example.tasker.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByUserId(Long userId);
}
