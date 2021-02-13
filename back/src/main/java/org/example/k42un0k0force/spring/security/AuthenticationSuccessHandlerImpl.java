package org.example.k42un0k0force.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String contentType = request.getContentType();
        if(contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE)){
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper mapper = new ObjectMapper();
            Object res = new Object() {
                public final String status = "ok";
            };
            response.getWriter().print(mapper.writeValueAsString(res));
        }else {
            handle(request, response, authentication);
        }
    }
}