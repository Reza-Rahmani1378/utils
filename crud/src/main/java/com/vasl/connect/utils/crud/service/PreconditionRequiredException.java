package com.vasl.connect.utils.crud.service;

import com.vasl.connect.utils.crud.api.InternationalizeConfig;
import org.springframework.context.i18n.LocaleContextHolder;

public class PreconditionRequiredException extends RuntimeException {
    private String message;

    public PreconditionRequiredException(String message) {
        this.message = message;
    }

    public PreconditionRequiredException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public PreconditionRequiredException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public PreconditionRequiredException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public PreconditionRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return InternationalizeConfig.getMessageForLocale(message, LocaleContextHolder.getLocale());
    }
}
