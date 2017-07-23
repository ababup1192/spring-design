package org.ababup1192.springdesign;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AgeLimitException(){
        super("Registration is impossible unless your age is 15 years or older.");
    }
}
