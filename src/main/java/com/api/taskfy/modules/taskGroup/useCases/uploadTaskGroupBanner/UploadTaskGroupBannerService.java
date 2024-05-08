package com.api.taskfy.modules.taskGroup.useCases.uploadTaskGroupBanner;

import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadTaskGroupBannerService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(
            String userId,
            String taskGroupId,
            MultipartFile banner
    ) {
        var taskGroupFound = this.taskGroupRepository.findById(taskGroupId);

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, taskGroupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getInviteStatus() != InviteStatus.ACCEPTED && taskGroupUser.getRequestStatus() != RequestStatus.ACCEPTED) {
            throw new UserNotBelongsToTaskGroupException();
        }

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER && taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        try {
            UUID uuid = UUID.randomUUID();
            String filename = uuid.toString() + "_" + banner.getOriginalFilename();

            byte[] bytes = banner.getBytes();

            String imagesPath = Paths.get("images/banner").toAbsolutePath().toString() + "/";

            Path path = Paths.get(imagesPath + filename);

            Files.write(path, bytes);

            var taskGroup = taskGroupFound.get();

            var oldBannerUrl = taskGroup.getBannerUrl();

            if (oldBannerUrl != null && !oldBannerUrl.isEmpty()) {
                var dividedOldBannerUrl =  oldBannerUrl.split("/");
                var oldFilename = dividedOldBannerUrl[dividedOldBannerUrl.length - 1];

                File oldFile = new File(imagesPath + oldFilename);

                if (oldFile.exists()) {
                    var oldFileIsDeleted = oldFile.delete();

                    if (!oldFileIsDeleted) {
                        System.out.println("Ocorreu um erro ao deletar o seguinte arquivo " + oldFilename);
                    }
                }
            }

            taskGroup.setBannerUrl("http://localhost:8080/task-group/banner/" + filename);

            this.taskGroupRepository.save(taskGroup);
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
    }
}
