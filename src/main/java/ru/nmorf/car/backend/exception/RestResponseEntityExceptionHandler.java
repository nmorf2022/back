package ru.nmorf.car.backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nmorf.car.backend.exception.impl.JwtAuthenticationException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {JwtAuthenticationException.class})
    public String handleException(JwtAuthenticationException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public String handleException(UsernameNotFoundException ex) {
        return ex.getMessage();
    }
}
