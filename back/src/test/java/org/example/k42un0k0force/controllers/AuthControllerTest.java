package org.example.k42un0k0force.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginUserWithForm() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "hello")
                .param("password", "world")
                .with(csrf()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void loginUserWithJson() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new Object() {
            public String username = "hello";
            public String password = "world";
        });

        String expectJson = new ObjectMapper().writeValueAsString(new Object() {
            public String status = "ok";
        });
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(expectJson));
    }

    @Test
    @WithMockUser
    public void logoutUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
        mockMvc.perform(logout())
                .andExpect(status().isFound());
        mockMvc.perform(get("/users"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
