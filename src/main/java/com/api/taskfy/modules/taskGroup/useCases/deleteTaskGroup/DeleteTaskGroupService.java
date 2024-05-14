package com.api.taskfy.modules.taskGroup.useCases.deleteTaskGroup;

import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskGroupService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    public void execute(String groupId) {
        this.taskGroupRepository.deleteById(groupId);
    }
}
