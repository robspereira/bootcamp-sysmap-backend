package br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions;

public class PurchaseIdNotFoundWithinCollectionException extends RuntimeException{
    public PurchaseIdNotFoundWithinCollectionException(String message){
        super(message);
    }
}
