package com.api.taskfy.modules.reward.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRewardDto {
    @Length(min = 10, message = "Title must have at least 10 characters")
    @Length(max = 50, message = "Title must have a maximum of 50 characters")
    public String title;

    @Length(min = 10, message = "Description must have at least 10 characters")
    @Length(max = 1000, message = "Description must have a maximum of 1000 characters")
    public String description;

}
