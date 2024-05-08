package com.api.taskfy.modules.taskGroup.useCases.uploadTaskGroupBanner;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class UploadTaskGroupBannerController {
    @Autowired
    UploadTaskGroupBannerService uploadTaskGroupBannerService;

    @PostMapping(path = "/banner/{taskGroupId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @RequestParam("banner") MultipartFile banner,
            @PathVariable("taskGroupId") String taskGroupId
    ) {
        this.uploadTaskGroupBannerService.execute(
                user.getId(),
                taskGroupId,
                banner
        );

        return ResponseEntity.ok().build();
    }
}
