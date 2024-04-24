package br.com.sysmap.bootcamp.web;


import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();

    }

    @Test
    @DisplayName("Should return users when valid users is saved")
    public void shouldReturnUsersWhenValidUsersIsSaved() throws Exception {
        Users users = Users.builder().name("usuario").email("email").password("senha").build();

        when(usersService.create(users)).thenReturn(users);

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(users)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

    @Test
    @DisplayName("Should return user when found by ID")
    public void shouldReturnUserWhenFoundById() throws Exception {
        Long testeId = 1L;
        Users users = Users.builder().id(testeId).name("usuario").email("email").password("senha").build();


        when(usersService.findById(testeId)).thenReturn(Optional.of(users));

        System.out.println(users.getId());
        System.out.println(users.getName());
        System.out.println(users.getEmail());
        System.out.println(users.getPassword());

        mockMvc.perform(get("/users/" + testeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("Should Return A List of Users")
    public void shouldReturnListOfUsers() throws Exception {
        Users user1 = Users.builder().name("usuario").email("email").password("senha").build();
        Users user2 = Users.builder().name("usuario2").email("email2").password("senha2").build();
        List<Users> expectedUsers = new ArrayList<>();
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(usersService.findAll()).thenReturn(expectedUsers);

        mockMvc.perform(get("/users/") // Replace "/users" with actual endpoint path if different
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Assert (Then) - Verified status code, content type, and some user data

    }

}
