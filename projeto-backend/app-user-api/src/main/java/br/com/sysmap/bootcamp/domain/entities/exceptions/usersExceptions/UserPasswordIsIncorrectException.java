package br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions;

public class UserPasswordIsIncorrectException extends RuntimeException{
    public UserPasswordIsIncorrectException(String message){
        super(message);
    }
}
