package com.api.taskfy.errors.ranking;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RankingExpiredException extends DefaultException {
    public static String message = "Ranking expired";

    public RankingExpiredException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
