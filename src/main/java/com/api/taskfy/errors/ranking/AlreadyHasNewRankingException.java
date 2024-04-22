package com.api.taskfy.errors.ranking;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyHasNewRankingException extends DefaultException {
    public static String message = "Already has a new ranking active";

    public AlreadyHasNewRankingException() {
        super(message, HttpStatus.CONFLICT);
    }
}
