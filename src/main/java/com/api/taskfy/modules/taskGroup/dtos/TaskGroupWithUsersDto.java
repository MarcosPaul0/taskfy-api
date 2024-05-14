package com.api.taskfy.modules.taskGroup.dtos;

import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.user.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class TaskGroupWithUsersDto {
    private TaskGroup taskGroup;
    private List<User> users;
}
