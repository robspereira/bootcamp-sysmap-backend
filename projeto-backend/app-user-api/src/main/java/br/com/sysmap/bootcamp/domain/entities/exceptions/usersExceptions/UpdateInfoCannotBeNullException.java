package br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions;

public class UpdateInfoCannotBeNullException extends RuntimeException {
    public UpdateInfoCannotBeNullException(String message){
        super(message);
    }
}
