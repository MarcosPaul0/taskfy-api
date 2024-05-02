package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUserByUserId;

import com.api.taskfy.modules.taskGroupUser.dtos.response.TaskGroupUserResponseDto;
import com.api.taskfy.modules.taskGroupUser.repositories.CustomTaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskGroupUserByUserIdService {
    @Autowired
    CustomTaskGroupUserRepository taskGroupUserRepository;

    public List<TaskGroupUserResponseDto> execute(User user) {
        return this.taskGroupUserRepository.findPendingInvitesByUserId(user.getId());
    }
}
