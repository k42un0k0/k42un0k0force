package org.example.k42un0k0force.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CorsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void denyAccess() throws Exception {
        mockMvc.perform(options("/test-cors")
                .header("Access-Control-Request-Method", "POST")
                .header("Origin", "http://example.com"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("Invalid CORS request")));
    }

    @Test
    public void allowAccess() throws Exception {
        mockMvc.perform(options("/test-cors")
                .header("Access-Control-Request-Method", "POST")
                .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk());
    }
}
