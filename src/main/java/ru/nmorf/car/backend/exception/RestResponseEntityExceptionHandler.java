package ru.nmorf.car.backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nmorf.car.backend.exception.impl.InvalidPasswordException;
import ru.nmorf.car.backend.exception.impl.TokenIsNotRefreshTypeException;
import ru.nmorf.car.backend.exception.impl.TokenValidationException;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public String handleException(UsernameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {AuthenticationException.class})
    public String handleException(AuthenticationException ex) {
        return "Invalid email/password combination";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {TokenValidationException.class})
    public String handleException(TokenValidationException ex) {
        return "Authentication problems";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {TokenIsNotRefreshTypeException.class})
    public String handleException(TokenIsNotRefreshTypeException ex) {
        return "Token for refresh must be refresh token";
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {InvalidPasswordException.class})
    public String handleException(InvalidPasswordException ex) {
        return "Old password is not correct";
    }
}
