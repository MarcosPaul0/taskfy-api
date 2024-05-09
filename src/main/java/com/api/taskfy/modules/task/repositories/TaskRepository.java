package com.api.taskfy.modules.task.repositories;

import com.api.taskfy.modules.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String>, CustomTaskRepository  {
    List<Task> findAllByGroupId(String groupId);
}
