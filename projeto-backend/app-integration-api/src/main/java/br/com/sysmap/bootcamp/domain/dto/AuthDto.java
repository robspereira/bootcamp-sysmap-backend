package br.com.sysmap.bootcamp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthDto {
    private String email;
    private String name;
    private String password;
    private String token;

}
