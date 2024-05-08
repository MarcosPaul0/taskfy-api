package com.api.taskfy.modules.taskGroup.useCases.getBanner;

import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GetBannerService {
    public Resource execute(String filename) {
        try {
            Path path = Paths.get("/home/marcos/workstation/projects/taskfy/images/banner/").resolve(filename);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() && !resource.isReadable()) {
                throw new TaskGroupNotFoundException();
            }

            return resource;
        } catch (IOException e) {
            throw new TaskGroupNotFoundException();
        }
    }
}
