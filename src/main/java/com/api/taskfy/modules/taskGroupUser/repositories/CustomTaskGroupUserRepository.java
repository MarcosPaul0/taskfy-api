package com.api.taskfy.modules.taskGroupUser.repositories;

import com.api.taskfy.modules.taskGroupUser.dtos.response.TaskGroupUserResponseDto;

import java.util.List;

public interface CustomTaskGroupUserRepository {
    List<TaskGroupUserResponseDto> findPendingInvitesByUserId(String userId);
}
