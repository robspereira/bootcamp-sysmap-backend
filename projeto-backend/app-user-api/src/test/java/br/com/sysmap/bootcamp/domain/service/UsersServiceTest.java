package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.exceptions.usersExceptions.UserNotFoundException;
import br.com.sysmap.bootcamp.domain.repository.UsersRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsersRepository usersRepository;



    @Test
    @DisplayName("Should return users when valid users is saved")
    public void shouldReturnUsersWhenValidUsersIsSaved(){
        String usersName = "testezinho";
        String usersEmail = "emailteste12";
        String usersPass = "testesenha";

        Users users = Users.builder().name(usersName).email(usersEmail).password(usersPass).build();

        when(usersRepository.save(any(Users.class))).thenReturn(users);

        Users savedUsers = usersService.create(users);

        String hashedPass = savedUsers.getPassword();

        boolean passwordMatches = passwordEncoder.matches(usersPass, hashedPass);

        assertEquals(users.getName(), savedUsers.getName());
        assertEquals(users.getEmail(), savedUsers.getEmail());
        assertTrue(passwordMatches);

    }

    @Test
    @DisplayName("Should return a list of all users")
    public void shouldReturnAListOfAllUsers(){

        List<Users> expectedUsers = Arrays.asList(
                Users.builder().name("Paulo").email("emaildopaulo").password("senhadopaulo").build(),
                Users.builder().name("Leandro").email("emaildoleandro").password("senhadoleandro").build()
        );

        when(usersRepository.findAll()).thenReturn(expectedUsers);

        List<Users> actualUsers = usersService.findAll();
        verify(usersRepository).findAll();


        System.out.println("Lista de Usuários Esperada:");
        for (Users user : expectedUsers) {
            System.out.println("------------------------------------");
            System.out.println("Nome: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Senha: " + user.getPassword());
            System.out.println("------------------------------------");
        }


        System.out.println("Lista de Usuários Retornada:");
        for (Users user : actualUsers) {
            System.out.println("------------------------------------");
            System.out.println("Nome: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Senha: " + user.getPassword());
            System.out.println("------------------------------------");
        }

        assertEquals(expectedUsers, actualUsers);


    }

    @Test
    @DisplayName("Should return user when searched by ID")
    public void shouldReturnUserWhenSearchedById(){
        Long expectedId = 1L;
        Users users = Users.builder().id(expectedId).name("Nome").email("testeEmail").password("password").build();

        when(usersRepository.findById(expectedId)).thenReturn(Optional.of(users));
        Optional<Users> userOptional = usersService.findById(expectedId);

        assertTrue(userOptional.isPresent());
        assertEquals(users, userOptional.get());
    }

    @Test
    @DisplayName("Should return user when searched by Email")
    public void shouldReturnUserWhenSearchedByEmail(){
        String email = "testeEmail";
        Users testUser = Users.builder().name("Nome").email(email).password("password").build();

        when(usersRepository.findByEmail(email)).thenReturn(Optional.of(testUser));
        Users user = usersService.findByEmail(email);

        assertEquals(testUser, user);
    }

    @Test
    @DisplayName("Should throw user not found exception when user is searched by email and not found")
    public void shouldThrowUserNotFoundExceptionWhenUserIsSearchedByEmailAndNotFound() throws Exception {
        String testEmail = "notFoundEmail";

        when(usersRepository.findByEmail(testEmail)).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> usersService.findByEmail(testEmail));
    }


}
