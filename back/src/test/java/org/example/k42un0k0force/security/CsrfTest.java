package org.example.k42un0k0force.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
// MEMO: 認証されてないとredirectされるため、認証済みにする
@WithMockUser
public class CsrfTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void denyAccess() throws Exception {
        mockMvc.perform(post("/test-csrf"))
                .andExpect(status().isFound());
    }

    @Test
    public void denyAccessWithInvalidToken() throws Exception {
        mockMvc.perform(post("/test-csrf")
                .with(SecurityMockMvcRequestPostProcessors.csrf().useInvalidToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void allowAccess() throws Exception {
        mockMvc.perform(post("/test-csrf")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void allowAccessWithHeader() throws Exception {
        mockMvc.perform(post("/test-csrf")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader()))
                .andExpect(status().isNotFound());
    }
}
