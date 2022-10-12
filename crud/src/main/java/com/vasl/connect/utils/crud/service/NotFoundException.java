package com.vasl.connect.utils.crud.service;


import com.vasl.connect.utils.crud.api.InternationalizeConfig;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return InternationalizeConfig.getMessageForLocale(message, LocaleContextHolder.getLocale());
    }
}
