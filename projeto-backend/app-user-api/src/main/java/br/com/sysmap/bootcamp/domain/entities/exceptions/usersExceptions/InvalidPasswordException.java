package br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message){
        super(message);
    }
}
