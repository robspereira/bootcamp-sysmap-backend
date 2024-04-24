package br.com.sysmap.bootcamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Wallet DTO")
public class WalletDto implements Serializable {


    private String email;
    private BigDecimal value;
    private Long user_id;

    public WalletDto(String email, BigDecimal value, Long user_id) {
        this.email = email;
        this.value = value;
        this.user_id = user_id;
    }


    
}
