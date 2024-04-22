package com.api.taskfy.errors.ranking;

import com.api.taskfy.errors.DefaultException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RankingNotFoundException extends DefaultException {
    public static String message = "Ranking not found";

    public RankingNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
