package be.mc.funfrench.digibooky.api.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DigibookyEntryPoint extends BasicAuthenticationEntryPoint {

    public static final String NAME_OF_REALM = "all-users";

    public DigibookyEntryPoint() {
        setRealmName(NAME_OF_REALM);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = String.format("{\"message\": \"%s\"}", authException.getMessage());
        response.getWriter().write(json);
    }
}
