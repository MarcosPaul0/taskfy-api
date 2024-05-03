package com.api.taskfy.modules.taskGroup.useCases.createTaskGroup;

import com.api.taskfy.errors.taskGroup.TaskGroupAlreadyExistsException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.taskGroup.dtos.CreateTaskGroupDto;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskGroupService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public TaskGroup execute(
            String ownerId,
            CreateTaskGroupDto createTaskGroupDto
    ) {
        var ownerFound = this.userRepository.findById(ownerId);

        if (ownerFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        var owner = ownerFound.get();
        var taskGroupAlreadyExists = this.taskGroupRepository.findByName(createTaskGroupDto.name);

        if (taskGroupAlreadyExists != null) {
            throw new TaskGroupAlreadyExistsException();
        }

        createTaskGroupDto.ownerId = ownerId;

        var newTaskGroup = new TaskGroup(createTaskGroupDto);
        newTaskGroup.setOwner(owner);

        var taskGroupCreated = this.taskGroupRepository.save(newTaskGroup);

        var newTaskGroupUser = new TaskGroupUser();

        newTaskGroupUser.setTaskGroupRole(TaskGroupRole.OWNER);
        newTaskGroupUser.setGroupId(taskGroupCreated.getId());
        newTaskGroupUser.setUser(owner);
        newTaskGroupUser.setInviteStatus(InviteStatus.ACCEPTED);
        newTaskGroupUser.setRequestStatus(RequestStatus.ACCEPTED);

        this.taskGroupUserRepository.save(newTaskGroupUser);

        return taskGroupCreated;
    }
}
