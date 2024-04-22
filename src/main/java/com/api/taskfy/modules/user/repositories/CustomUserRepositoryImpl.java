package com.api.taskfy.modules.user.repositories;

import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findByGroup(String groupId, String emailPattern, String usernamePattern) {
        String sql = "SELECT u.id, u.name, u.email, u.role, u.created_at, u.updated_at " +
                "FROM taskfy_user u " +
                "JOIN taskfy_task_group_user tgu ON u.id = tgu.user_id " +
                "JOIN taskfy_task_group tg ON tgu.task_group_id = tg.id " +
                "WHERE tg.id = ?";

        boolean hasEmailPattern = !(emailPattern == null || emailPattern.isEmpty() || emailPattern.isBlank());

        if (hasEmailPattern) {
            sql = sql + " AND LOWER(u.email) LIKE " + "LOWER('%" + emailPattern + "%')";
        }

        boolean hasUsernamePattern = !(usernamePattern == null || usernamePattern.isEmpty() || usernamePattern.isBlank());

        if (hasUsernamePattern) {
            sql = sql + " AND LOWER(u.name) LIKE " + "LOWER('%" + usernamePattern + "%')";
        }

        return jdbcTemplate.query(sql, new Object[]{ groupId }, (resultSet, rowNum) -> {
            var user = new User();

            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));
            user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            user.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

            return user;
        });
    }
}
