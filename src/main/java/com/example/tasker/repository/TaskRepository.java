package com.example.tasker.repository;

import com.example.tasker.model.Priority;
import com.example.tasker.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userId AND " +
           "(:startDate IS NULL OR t.dueDate >= :startDate) AND " +
           "(:endDate IS NULL OR t.dueDate <= :endDate) AND " +
           "(:priority IS NULL OR t.priority = :priority) AND " +
           "(:completedStatus IS NULL OR t.completed = :completedStatus)")
    List<TaskEntity> findTasksByFilters(@Param("userId") Long userId,
                                        @Param("startDate") Instant startDate,
                                        @Param("endDate") Instant endDate,
                                        @Param("priority") Priority priority,
                                        @Param("completedStatus") Boolean completedStatus);

    List<TaskEntity> findAllByUserId(Long userId);
}
