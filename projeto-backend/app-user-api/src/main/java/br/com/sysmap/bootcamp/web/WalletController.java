package br.com.sysmap.bootcamp.web;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.service.UsersService;
import br.com.sysmap.bootcamp.domain.service.WalletService;
import br.com.sysmap.bootcamp.dto.WalletDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final UsersService usersService;
    private final WalletService walletService;

    @Operation(summary = "Adiciona créditos na carteira de um usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Créditos adicionados com sucesso"),
    })
    @PostMapping("/credit/{value}")
    public ResponseEntity<Wallet> addCredit(@PathVariable BigDecimal value) {

        return ResponseEntity.ok(this.walletService.credit(value));
    }

    @Operation(summary = "Retorna o balanço da carteira", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carteira foi encontrada"),
    })
    @Parameter(description = "Valor a ser adicionado", name = "value", example = "400", required = true)
    @GetMapping("/")
    public ResponseEntity<Wallet> wallet() {
        return ResponseEntity.ok(this.walletService.getWallet());
    }


}
