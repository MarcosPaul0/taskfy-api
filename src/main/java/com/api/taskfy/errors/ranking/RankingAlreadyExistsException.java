package com.api.taskfy.errors.ranking;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RankingAlreadyExistsException extends DefaultException {
    public static String message = "Ranking already exists";

    public RankingAlreadyExistsException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
