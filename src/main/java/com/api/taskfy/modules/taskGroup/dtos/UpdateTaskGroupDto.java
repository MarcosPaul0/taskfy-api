package com.api.taskfy.modules.taskGroup.dtos;

import com.api.taskfy.constants.RegularExpressions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskGroupDto {
    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 100, message = "Name must have a maximum of 100 characters")
    public String name;

    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 1000, message = "Name must have a maximum of 1000 characters")
    public String description;

    @Pattern(regexp = RegularExpressions.HEX, message = "Primary color is invalid")
    public String primaryColor;

    public Boolean isPrivate;
}
