package br.com.sysmap.bootcamp.config;

import br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions.AlbumIsAlreadyInCollectionException;
import br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions.PurchaseIdNotFoundWithinCollectionException;
import br.com.sysmap.bootcamp.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler(AlbumIsAlreadyInCollectionException.class)
    public ResponseEntity<ErrorResponseDto> handleAlbumIsAlreadyInCollectionException(AlbumIsAlreadyInCollectionException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(PurchaseIdNotFoundWithinCollectionException.class)
    public ResponseEntity<ErrorResponseDto> handlePurchaseIdNotFoundWithinCollectionException( PurchaseIdNotFoundWithinCollectionException exception) {
        return ResponseEntity.notFound().build();
    }
}
