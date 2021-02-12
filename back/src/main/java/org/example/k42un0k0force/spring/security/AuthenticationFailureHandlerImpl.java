package org.example.k42un0k0force.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.k42un0k0force.constants.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String contentType = request.getContentType();
        switch (contentType) {
            case ContentType.JSON:
                response.setContentType(ContentType.JSON);
                response.setStatus(HttpServletResponse.SC_OK);
                ObjectMapper mapper = new ObjectMapper();
                Object res = new Object() {
                    public final String status = "failed";
                };
                response.getWriter().print(mapper.writeValueAsString(res));
                break;
            default:
                super.onAuthenticationFailure(request, response, exception);
        }
    }
}