package pet.projects.bookshop.integration;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import pet.projects.bookshop.dto.Role;

import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookManageControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void creteBookByUser() throws Exception {
        registerUser();
        final var userDetails = buildUserDetails("user", "user", Role.USER);
        final var request = post("/management/book")
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id" : 10000,
                            "name" : "name",
                            "author" : "author",
                            "description" : "description",
                            "price" : 100.00,
                            "purchases" : []
                        }
                        """);

        mockMvc.perform(request)
                .andExpect(
                        status().is(403)
                );
    }

    @Test
    public void creteBookByAdmin() throws Exception {
        registerAdmin();
        final var userDetails = buildUserDetails("admin", "admin", Role.ADMIN);
        final var createBook = post("/management/book")
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id" : 10000,
                            "name" : "name1",
                            "author" : "author1",
                            "description" : "description",
                            "price" : 100.00,
                            "purchases" : []
                        }
                        """);
        final var findBook = get("/search/book/name")
                .param("name", "name1")
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(createBook)
                .andExpect(
                        status().isCreated()
                );

        mockMvc.perform(findBook)
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.content()
                                .json("""
                                [
                                {
                                    "name" : "name1",
                                    "author" : "author1"
                                }
                                ]
                                """)
                );
    }

    private void registerUser() throws Exception {
        var createBook = post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id" : 10001,
                            "username" : "user",
                            "role" : "USER",
                            "password" : "user",
                            "moneyInAccount" : 10000.00,
                            "purchases" : []
                        }
                        """);

        mockMvc.perform(createBook)
                .andExpect(
                        status().isOk()
                );
    }

    private void registerAdmin() throws Exception {
        var request = post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id" : 10000,
                            "username" : "admin",
                            "role" : "ADMIN",
                            "password" : "admin",
                            "moneyInAccount" : 10000.00,
                            "purchases" : []
                        }
                        """);

        mockMvc.perform(request)
                .andExpect(
                        status().isOk()
                );
    }

    public UserDetails buildUserDetails(String user, String password, Role role) {
        final var authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return new User(user, password, authorities);
    }
}
