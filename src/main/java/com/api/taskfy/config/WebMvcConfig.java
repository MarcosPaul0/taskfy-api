package com.api.taskfy.config;

import com.api.taskfy.modules.taskGroup.policy.TaskGroupPolicy;
import com.api.taskfy.modules.taskGroupUser.policies.GroupsByUserPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public TaskGroupPolicy taskGroupPolicy() {
        return new TaskGroupPolicy();
    }

    @Bean GroupsByUserPolicy groupsByUserPolicy() {
        return new GroupsByUserPolicy();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.taskGroupPolicy())
                .addPathPatterns("/task-group/{groupId}");

        registry.addInterceptor(this.groupsByUserPolicy())
                .addPathPatterns("/task-group-user/group/{groupId}");
    }

}
