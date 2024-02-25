package com.example.clouddiploma.jwt;

import com.example.clouddiploma.model.User;
import com.example.clouddiploma.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_BEARER = "Bearer ";

    private final JwtGenerator tokenGenerator;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtGenerator tokenGenerator, UserRepository userRepository) {
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
    }


    private String getJWTFromRequest(HttpServletRequest request) {
        final var bearerToken = request.getHeader(HEADER_AUTHORIZATION);
        return hasText(bearerToken) && bearerToken.startsWith(PREFIX_BEARER) ?
                bearerToken.substring(7) :
                null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getJWTFromRequest((HttpServletRequest) servletRequest);
        if (token != null && tokenGenerator.validateToken(token)) {
            String username = tokenGenerator.getUsernameFromJWT(token);
            User user = userRepository.findByUsername(username);
            JwtUsers jwtUsers = new JwtUsers(user);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,jwtUsers.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}