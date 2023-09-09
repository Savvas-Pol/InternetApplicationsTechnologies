package com.savvas.jobapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GeneralBadRequest extends RuntimeException {
    private String resourceName;
    private String reason;

    public GeneralBadRequest(String resourceName, String reason) {
        super(String.format("%s error : '%s'", resourceName, reason));
        this.resourceName = resourceName;
        this.reason = reason;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getReason() {
        return reason;
    }
}