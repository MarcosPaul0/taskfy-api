package com.api.taskfy.modules.user.dtos;

import org.hibernate.validator.constraints.Length;

public class UpdateUserDto {
    @Length(min = 10, message = "Name must have at least 10 characters")
    @Length(max = 100, message = "Name must have a maximum of 100 characters")
    public String name;
}
