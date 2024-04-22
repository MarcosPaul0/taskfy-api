package com.api.taskfy.errors.reward;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RewardNotFoundException extends DefaultException {
    public static String message = "Reward not found";

    public RewardNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
