package br.com.sysmap.bootcamp.domain.service;


import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.InvalidPasswordException;
import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.UpdateInfoCannotBeNullException;
import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.UserEmailAlreadyExistsException;
import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.UserNotFoundException;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import br.com.sysmap.bootcamp.dto.AuthDto;
import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final WalletRepository walletRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED)
    public Users create(Users user) {

        validateInfo(user);

        Optional<Users> usersOptional = this.usersRepository.findByEmail(user.getEmail());

        if (usersOptional.isPresent()) {
            throw new UserEmailAlreadyExistsException("The provided email was already used for registration!");
        }

        user = user.toBuilder().password(this.passwordEncoder.encode(user.getPassword())).build();

        Wallet wallet = Wallet.builder()
                .balance(new BigDecimal(200))
                .points(0L)
                .lastUpdate(LocalDateTime.now())
                .users(user)
                .build();

        this.walletRepository.save(wallet);


        log.info("Saving user: {}", user);
        return this.usersRepository.saveAndFlush(user);
    }

    public Users updateUser(Users user) {

        Users contextUser;
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        try {
            contextUser = getUser();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user from Security Context", e);
        }

        Optional<Users> verifyUserEmail = usersRepository.findByEmail(user.getEmail());

        if(verifyUserEmail.isPresent() && !user.getEmail().equals(contextUser.getEmail())) {
            throw new UserEmailAlreadyExistsException("Email já está em uso");
        }

        validateInfo(user);

        contextUser.setName(user.getName());
        contextUser.setEmail(user.getEmail());
        if (!passwordEncoder.matches(user.getPassword(), contextUser.getPassword())) {
            contextUser.setPassword(hashedPassword);
        } else {
            throw new RuntimeException("Password provided is the same as before");
        }

        return usersRepository.save(contextUser);

    }

    public Optional<Users> findById(Long id){
        return this.usersRepository.findById(id);
    }

    public List<Users> findAll(){
        return this.usersRepository.findAll();
    }

    public Users findByEmail(String email){

        return this.usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public AuthDto auth(AuthDto authDto) {
        Users users = this.findByEmail(authDto.getEmail());

        if (!this.passwordEncoder.matches(authDto.getPassword(), users.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        StringBuilder password = new StringBuilder().append(users.getEmail()).append(":").append(users.getPassword());

        return AuthDto.builder().email(users.getEmail()).token(
                Base64.getEncoder().withoutPadding().encodeToString(password.toString().getBytes())
        ).id(users.getId()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException{
        Optional<Users> usersOptional = this.usersRepository.findByEmail(username);

        return usersOptional.map(users -> new User(users.getEmail(), users.getPassword(), new ArrayList<GrantedAuthority>()))
                .orElseThrow(() -> new UserNotFoundException("User not found" + username));


    }

    private void validateInfo(Users user) {

        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank()) {

            throw new UpdateInfoCannotBeNullException("O campo email não pode estar vazio!");

        }

        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank() ) {

            throw new IllegalArgumentException("O campo nome não pode estar vazio!");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {

            throw new IllegalArgumentException("O campo senha não pode estar vazio!");

        }

    }

    public Users getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();
        return findByEmail(username);
    }


}
