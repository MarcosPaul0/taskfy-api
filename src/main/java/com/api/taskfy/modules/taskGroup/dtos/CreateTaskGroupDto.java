package com.api.taskfy.modules.taskGroup.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class CreateTaskGroupDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 100, message = "Name must have a maximum of 100 characters")
    public String name;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 1000, message = "Name must have a maximum of 1000 characters")
    public String description;

    @NotNull(message = "Primary color is required")
    @NotBlank(message = "Primary color is required")
    @Pattern(regexp = RegularExpressions.HEX, message = "Primary color is invalid")
    public String primaryColor;

    @NotNull(message = "Is private field is required")
    public Boolean isPrivate;

    @JsonIgnore
    public String ownerId;
}
