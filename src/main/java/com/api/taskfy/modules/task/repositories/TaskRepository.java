package com.api.taskfy.modules.task.repositories;

import com.api.taskfy.modules.task.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String>, CustomTaskRepository  {
    Page<Task> findAllByGroupIdAndTitleContaining(String groupId, String title, Pageable pageable);
}
