package br.com.sysmap.bootcamp.web;


import br.com.sysmap.bootcamp.dto.AuthDto;
import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") //em todos os controllers
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    @Operation(summary = "Salva o usuário no banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já está cadastrado"),
    })
    @Parameters(value = {
            @Parameter(description = "Nome do usuário", name = "name", example = "Daniel"),
            @Parameter(description = "Email do usuário", name = "email", example = "usuario@gmail.com"),
            @Parameter(description = "Senha do usuário", name = "password", example = "123")
    })
    @PostMapping("/create")
    public ResponseEntity<Users> create(@RequestBody Users user){
        Users userCriado = this.usersService.create(user);
        URI local = URI.create("/users/" + userCriado.getId());
        return ResponseEntity.created(local).body(userCriado);
    }


    @Operation(summary = "Procura usuário pelo ID", method = "GET")
    @Parameter(description = "Id do usuário", name = "id", example = "3", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não autenticado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id){
        Optional<Users> response = this.usersService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Atualiza os dados do usuário com as informações fornecidas no body", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do usuário foram atualizados"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado"),
            @ApiResponse(responseCode = "400", description = "Um ou mais campos estão vazios"),
    })
    @Parameters(value = {
            @Parameter(description = "Nome do usuário", name = "name", example = "Daniel", required = true),
            @Parameter(description = "Email do usuário", name = "email", example = "usuario@gmail.com", required = true),
            @Parameter(description = "Senha do usuário", name = "password", example = "123", required = true)
    })
    @PutMapping(value = "/update")
    public ResponseEntity<Users> update(@RequestBody Users user) {
        Users updatedUser = usersService.updateUser(user);
        return ResponseEntity.ok(this.usersService.updateUser(updatedUser));
    }

    @Operation(summary = "Recupera uma lista com todos os usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista recuperada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
    })
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> response = this.usersService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Autentica um usuário e cria o token de autenticação", method = "GET")
    @Parameters(value = {
            @Parameter(description = "Email do usuário", name = "email", example = "usuario@gmail.com", required = true),
            @Parameter(description = "Senha do usuário", name = "password", example = "123", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Credenciais invalidas"),
    })
    @PostMapping(value = "/auth")
    public ResponseEntity<AuthDto> auth(@RequestBody AuthDto user){
        return ResponseEntity.ok(this.usersService.auth(user));
    }
}
