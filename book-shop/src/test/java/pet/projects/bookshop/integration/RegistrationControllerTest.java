package pet.projects.bookshop.integration;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerUser() throws Exception {
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

    @Test
    public void registerAlreadyRegisteredUser() throws Exception {
        var request = post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id" : 10000,
                            "username" : "user",
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

        mockMvc.perform(request)
                .andExpect(
                        status().isBadRequest()
                );
    }
}
