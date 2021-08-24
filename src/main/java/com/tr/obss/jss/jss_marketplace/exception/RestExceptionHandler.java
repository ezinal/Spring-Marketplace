package com.tr.obss.jss.jss_marketplace.exception;

import java.io.IOException;

import com.tr.obss.jss.jss_marketplace.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse badRequest(Exception ex)
    {
        return new MessageResponse("Bad Request");
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse unKnownException(Exception ex)
    {
        return new MessageResponse("Requested object not found");
    }
}