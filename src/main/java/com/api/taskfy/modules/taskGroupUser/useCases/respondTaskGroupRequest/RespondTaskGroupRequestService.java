package com.api.taskfy.modules.taskGroupUser.useCases.respondTaskGroupRequest;

import com.api.taskfy.errors.taskGroup.TaskGroupNotBelongsToUserException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotFoundException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.dtos.RespondTaskGroupRequestDto;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespondTaskGroupRequestService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    public void execute(String userId, String groupUserId, RespondTaskGroupRequestDto respondTaskGroupRequestDto) {
        var taskGroupUserFound = this.taskGroupUserRepository.findById(groupUserId);

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupUserNotFoundException();
        }

        var updatedTaskGroupUser = taskGroupUserFound.get();

        var taskGroupFound = this.taskGroupRepository.findById(updatedTaskGroupUser.getGroupId());

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();

        if (!userId.equals(taskGroup.getOwner().getId())) {
            throw new TaskGroupNotBelongsToUserException();
        }

        updatedTaskGroupUser.setRequestStatus(RequestStatus.valueOf(respondTaskGroupRequestDto.requestStatus));

        this.taskGroupUserRepository.save(updatedTaskGroupUser);
    }
}
