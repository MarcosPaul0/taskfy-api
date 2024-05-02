package com.api.taskfy.modules.taskGroupUser.repositories;

import com.api.taskfy.modules.taskGroupUser.dtos.response.TaskGroupResponseDto;
import com.api.taskfy.modules.taskGroupUser.dtos.response.TaskGroupUserResponseDto;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomTaskGroupUserRepositoryImpl implements CustomTaskGroupUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TaskGroupUserResponseDto> findPendingInvitesByUserId(String userId) {
        String sql = "SELECT tgu.id, tgu.task_group_role, tgu.invite_status, tgu.created_at, tgu.updated_at, tgu.user_id, tgu.task_group_id, tg.name, tg.description " +
                "FROM taskfy_task_group_user tgu " +
                "JOIN taskfy_task_group tg ON tgu.task_group_id = tg.id " +
                "WHERE tgu.invite_status = 'PENDING' AND tgu.user_id = ?";

        return this.jdbcTemplate.query(sql, new Object[]{ userId }, (resultSet, rowNum) -> {
            var taskGroup = new TaskGroupResponseDto();
            var taskGroupUser = new TaskGroupUserResponseDto();

            taskGroup.setId(resultSet.getString("task_group_id"));
            taskGroup.setName(resultSet.getString("name"));
            taskGroup.setDescription(resultSet.getString("description"));

            taskGroupUser.setTaskGroup(taskGroup);
            taskGroupUser.setTaskGroupRole(TaskGroupRole.valueOf(resultSet.getString("task_group_role")));
            taskGroupUser.setUserId(resultSet.getString("user_id"));
            taskGroupUser.setId(resultSet.getString("id"));
            taskGroupUser.setInviteStatus(InviteStatus.valueOf(resultSet.getString("invite_status")));
            taskGroupUser.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            taskGroupUser.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

            return taskGroupUser;
        });
    }
}
