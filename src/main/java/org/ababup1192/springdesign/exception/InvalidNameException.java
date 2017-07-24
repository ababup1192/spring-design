package org.ababup1192.springdesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidNameException(char firstLetter) {
        super("The initial letter of your name is '" + firstLetter + "'. " +
                "But, the initial letter of the name must be 'J'.");
    }
}
