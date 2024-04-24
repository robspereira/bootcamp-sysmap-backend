package br.com.sysmap.bootcamp.domain.service;


import br.com.sysmap.bootcamp.domain.enums.WeekdayPoints;
import br.com.sysmap.bootcamp.dto.WalletDto;
import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import java.util.stream.Stream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final UsersService usersService;
    private final WalletRepository walletRepository;

    public void debit(WalletDto walletDto){

        Users users = usersService.findByEmail(walletDto.getEmail());
        Wallet wallet = walletRepository.findByUsers(users).orElseThrow();
        wallet.setBalance(wallet.getBalance().subtract(walletDto.getValue()));

        LocalDate day = LocalDate.now();
        wallet.setPoints(wallet.getPoints() + pointsCalculator(day));

        walletRepository.save(wallet);
    }

    public Wallet credit(BigDecimal value) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersService.findByEmail(authentication.getName());
        Wallet wallet = walletRepository.findByUsers(users).orElseThrow();
        wallet.setBalance(wallet.getBalance().add(value));

        return walletRepository.save(wallet);
    }

    public Wallet getWallet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersService.findByEmail(authentication.getName());

        return this.walletRepository.findByUsers(users).orElseThrow();

    }

    private Integer pointsCalculator(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        WeekdayPoints pointsCalc = WeekdayPoints.fromDayOfWeek(dayOfWeek);
        return pointsCalc.getPoints();
    }

}
