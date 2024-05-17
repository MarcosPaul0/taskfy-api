package com.api.taskfy.modules.reward.dtos;

import com.api.taskfy.constants.RegularExpressions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class CreateRewardDto {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Length(min = 10, message = "Title must have at least 10 characters")
    @Length(max = 50, message = "Title must have a maximum of 50 characters")
    public String title;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    @Length(min = 10, message = "Description must have at least 10 characters")
    @Length(max = 1000, message = "Description must have a maximum of 1000 characters")
    public String description;

    @NotNull(message = "Ranking id color is required")
    @NotBlank(message = "Ranking id color is required")
    @Pattern(regexp = RegularExpressions.UUID, message = "Ranking id is invalid")
    public String rankingId;
}
