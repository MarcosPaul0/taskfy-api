package com.api.taskfy.modules.taskGroup.useCases.updateTaskGroup;

import com.api.taskfy.modules.taskGroup.dtos.UpdateTaskGroupDto;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskGroupService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    // TODO Tratar permissões
    public TaskGroup execute(TaskGroup taskGroup, UpdateTaskGroupDto updateTaskGroupDto) {
        if (updateTaskGroupDto.name != null && !updateTaskGroupDto.name.isBlank()) {
            taskGroup.setName(updateTaskGroupDto.name);
        }

        if (updateTaskGroupDto.description != null && !updateTaskGroupDto.description.isBlank()) {
            taskGroup.setDescription(updateTaskGroupDto.description);
        }

        if (updateTaskGroupDto.isPrivate != null) {
            taskGroup.setIsPrivate(updateTaskGroupDto.isPrivate);
        }

        if (updateTaskGroupDto.primaryColor != null && !updateTaskGroupDto.primaryColor.isBlank()) {
            taskGroup.setPrimaryColor(updateTaskGroupDto.primaryColor);
        }

        return this.taskGroupRepository.save(taskGroup);
    }
}
