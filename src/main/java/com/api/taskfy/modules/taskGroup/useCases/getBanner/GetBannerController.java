package com.api.taskfy.modules.taskGroup.useCases.getBanner;

import com.api.taskfy.constants.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class GetBannerController {
    @Autowired
    GetBannerService getBannerService;

    @GetMapping("/banner/{filename:.+}")
    public ResponseEntity<Resource> handle(@PathVariable("filename") String filename) {
        var resource = this.getBannerService.execute(filename);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}
