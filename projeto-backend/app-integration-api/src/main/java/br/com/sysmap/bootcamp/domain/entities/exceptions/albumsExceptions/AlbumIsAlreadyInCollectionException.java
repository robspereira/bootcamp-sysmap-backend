package br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions;

public class AlbumIsAlreadyInCollectionException extends RuntimeException {
    public AlbumIsAlreadyInCollectionException(String message) {
        super(message);
    }
}
