package com.api.taskfy.modules.task.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTaskRepositoryImpl implements CustomTaskRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deleteTaskById(String taskId) {
        String sql = "DELETE FROM taskfy_task t WHERE t.id = ?";

        jdbcTemplate.update(sql, taskId);
    }
}
