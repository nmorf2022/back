package ru.nmorf.car.backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nmorf.car.backend.exception.impl.*;


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

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {UserEmailAlreadyExistsException.class})
    public String handleException(UserEmailAlreadyExistsException ex) {
        return "User with this email already exists";
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {SwitchToCadetNotAvailableException.class})
    public String handleException(SwitchToCadetNotAvailableException ex) {
        return "A user with this email cannot be a cadet because the email does not exist or is not active, or is not an applicant";
    }
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {UserNotFoundException.class})
    public String handleException(UserNotFoundException ex) {
        return "User with this email not found";
    }
}
