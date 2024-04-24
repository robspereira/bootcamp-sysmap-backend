package br.com.sysmap.bootcamp.web;

import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.service.AlbumService;
import br.com.sysmap.bootcamp.domain.service.integration.SpotifyApi;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Operation(summary = "Pesquisa de albums", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa feita com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
    })
    @Parameters(value = {
            @Parameter(description = "Descrição da pesquisa", name = "search", example = "Ana Castela"),

    })
    @GetMapping("/all")
    public ResponseEntity<List<AlbumModel>> getAlbums(@RequestParam("search") String search) throws IOException, ParseException, org.apache.hc.core5.http.ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(this.albumService.getAlbums(search));

    }

    @Operation(summary = "Realiza a compra de um álbum", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
    })
    @Parameters(value = {
            @Parameter(description = "Nome do álbum ou artista", name = "name", example = "MC Cabelinho"),
            @Parameter(description = "ID do álbum no spotify", name = "idSpotify", example = "1WQBwwssN6r8DSjUlkyUGW"),
            @Parameter(description = "URL da imagem", name = "imageUrl", example = "https://i.scdn.co/image/ab67616d0000b2735384a1eed3cf01a9f11e0aaa"),
            @Parameter(description = "Valor do álbum", name = "value", example = "85"),
    })
    @PostMapping("/sale")
    public ResponseEntity<Album> saveAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(this.albumService.saveAlbum(album));
    }

    @Operation(summary = "Retorna todos os álbuns da coleção do usuário autenticado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coleção recuperada com sucesso"),
    })
    @GetMapping("/my-collection")
    public ResponseEntity<List<Album>> retrieveAlbumCollection(){
        return ResponseEntity.ok(this.albumService.getCollection());
    }

    @Operation(summary = "Remove um álbum da coleção", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Álbum removido com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "ID de compra não foi encontrado na coleção"),
    })
    @Parameter(description = "ID do álbum", name = "id", example = "1")

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeAlbum(@PathVariable Long id){
        this.albumService.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }


}
