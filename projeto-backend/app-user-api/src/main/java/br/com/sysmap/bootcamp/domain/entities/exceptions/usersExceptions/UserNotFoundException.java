package br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
