package br.com.sysmap.bootcamp.config;

import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.*;
import br.com.sysmap.bootcamp.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UserPasswordIsIncorrectException.class)
    public ResponseEntity<ErrorResponseDto> handleUserPasswordIsIncorrectException(UserPasswordIsIncorrectException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UpdateInfoCannotBeNullException.class)
    public ResponseEntity<ErrorResponseDto> handleUpdateInfoCannotBeNullException(UpdateInfoCannotBeNullException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPasswordException(InvalidPasswordException exception) {
        return ResponseEntity.badRequest().build();
    }

}
