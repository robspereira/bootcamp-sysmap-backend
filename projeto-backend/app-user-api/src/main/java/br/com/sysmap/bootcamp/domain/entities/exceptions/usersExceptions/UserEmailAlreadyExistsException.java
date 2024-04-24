package br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
