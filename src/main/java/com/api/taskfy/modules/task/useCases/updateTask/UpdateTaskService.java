package com.api.taskfy.modules.task.useCases.updateTask;

import com.api.taskfy.errors.task.TaskNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.ranking.repositories.UserPlacementRepository;
import com.api.taskfy.modules.task.dtos.UpdateTaskDto;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.task.enums.TaskStatus;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateTaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    UserPlacementRepository userPlacementRepository;

    private void updateUserPoints(String userId, String groupId, Integer points) {
        var rankingFound = this.rankingRepository.findByPeriodAndGroupId(LocalDateTime.now(), groupId);

        if (rankingFound.isEmpty()) {
            return;
        }

        var usersPlacementList = rankingFound.getFirst().getUserPlacementList();

        if (usersPlacementList.isEmpty()) {
            return;
        }

        var userPlacementFound = usersPlacementList
                .stream()
                .filter(userPlacement -> userPlacement.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (userPlacementFound == null) {
            return;
        }

        var currentPoints = userPlacementFound.getPoints();

        userPlacementFound.setPoints(currentPoints + points);

        this.userPlacementRepository.save(userPlacementFound);
    }

    public Task execute(String userId, String taskId, UpdateTaskDto updateTaskDto) {
        var taskFound = this.taskRepository.findById(taskId);

        if (taskFound.isEmpty()) {
            throw new TaskNotFoundException();
        }

        var task = taskFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, task.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (
                !taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.OWNER) &&
                        !taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.MANAGER)
        ) {
            throw new UserNotHavePermissionException();
        }

        if (updateTaskDto.userId != null && !updateTaskDto.userId.isBlank()) {
            var taskOwner = this.userRepository.findById(updateTaskDto.userId);

            if (taskOwner.isEmpty()) {
                throw new UserNotFoundException();
            }

            task.setUser(taskOwner.get());
        }

        if (updateTaskDto.title != null && !updateTaskDto.title.isBlank()) {
            task.setTitle(updateTaskDto.title);
        }

        if (updateTaskDto.description != null && !updateTaskDto.description.isBlank()) {
            task.setDescription(updateTaskDto.description);
        }

        if (updateTaskDto.status != null && !updateTaskDto.status.isBlank()) {

            var pointsToAdd = task.getPoints();

            if (TaskStatus.valueOf(updateTaskDto.status) == TaskStatus.CHECKED) {
                this.updateUserPoints(userId, task.getGroupId(), task.getPoints());
            }

            if (TaskStatus.valueOf(updateTaskDto.status) != TaskStatus.CHECKED && task.getStatus() == TaskStatus.CHECKED) {
                var pointsToSubtract = pointsToAdd * -1;

                this.updateUserPoints(userId, task.getGroupId(), pointsToSubtract);
            }

            task.setStatus(TaskStatus.valueOf(updateTaskDto.status));
        }

        if (updateTaskDto.dueDate != null) {
            task.setDueDate(updateTaskDto.dueDate);
        }

        if (updateTaskDto.points != null) {
            task.setPoints(updateTaskDto.points);
        }

        if (updateTaskDto.isActive != null) {
            task.setIsActive(updateTaskDto.isActive);
        }

        return this.taskRepository.save(task);
    }
}
