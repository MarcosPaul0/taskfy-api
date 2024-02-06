package com.api.taskfy.modules.taskGroup.repositories;

import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, String> {
    TaskGroup findByName(String name);
    List<TaskGroup> findByOwnerId(String ownerId);
    List<TaskGroup> findAllByNameContainingIgnoreCaseAndIsPrivateFalseOrderByCreatedAtAsc(String name);
    List<TaskGroup> findAllByIsPrivateFalseOrderByCreatedAtAsc();
}
