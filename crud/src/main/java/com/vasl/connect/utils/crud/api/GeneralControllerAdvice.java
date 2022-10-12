package com.vasl.connect.utils.crud.api;

import com.vasl.connect.utils.crud.api.model.ErrorResponse;
import com.vasl.connect.utils.crud.service.NotFoundException;
import com.vasl.connect.utils.crud.service.PreconditionRequiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse noDataFoundExceptionHandler(NotFoundException e) {
        logger.warn("NoDataFoundExceptionHandler", e);
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(PreconditionRequiredException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    @ResponseBody
    public ErrorResponse preconditionRequiredExceptionHandler(PreconditionRequiredException e) {
        logger.warn("PreconditionRequiredException", e);
        return ErrorResponse.builder().message(e.getMessage()).build();
    }
}
