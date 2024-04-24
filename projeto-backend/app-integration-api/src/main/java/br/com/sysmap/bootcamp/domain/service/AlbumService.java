package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions.AlbumIsAlreadyInCollectionException;
import br.com.sysmap.bootcamp.domain.entities.exceptions.albumsExceptions.PurchaseIdNotFoundWithinCollectionException;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.repository.AlbumRepository;
import br.com.sysmap.bootcamp.domain.service.integration.SpotifyApi;
import br.com.sysmap.bootcamp.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
@Service
public class AlbumService {

    private final Queue queue;
    private final RabbitTemplate template;
    private final SpotifyApi spotifyApi;
    private final AlbumRepository albumRepository;
    private final UsersService usersService;


    public List<AlbumModel> getAlbums(String search) throws IOException, ParseException, SpotifyWebApiException, java.text.ParseException {
        return this.spotifyApi.getAlbums(search);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Album saveAlbum(Album album) {

        album.setUsers(getUser());

        if(albumRepository.findByIdSpotify(album.getIdSpotify()).isPresent()){
            throw new AlbumIsAlreadyInCollectionException("This album is already in your collection!");
        }

        Album albumSaved = albumRepository.save(album);

        WalletDto walletDto = new WalletDto(albumSaved.getUsers().getEmail(), albumSaved.getValue(), albumSaved.getUsers().getId());
        this.template.convertAndSend(queue.getName(), walletDto);

        return albumSaved;
    }

    private Users getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();
        return usersService.findByEmail(username);
    }

    public List<Album> getCollection(){
        Users user = getUser();
        return albumRepository.findAllByUsers(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Void> deleteAlbum(Long id){

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new PurchaseIdNotFoundWithinCollectionException("Purchase Id was not found."));

        albumRepository.delete(album);

        return ResponseEntity.ok().build();
    }


}