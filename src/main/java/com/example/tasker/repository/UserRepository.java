package com.example.tasker.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tasker.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    
}
