package com.example.neksus.config;

import com.example.neksus.models.User;
import com.example.neksus.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;

    @Autowired
    public CustomAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (authService.authenticate(email, password)) {
            Optional<User> optionalUser = authService.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getIsBanned() == 'Y') {
                    throw new BadCredentialsException("This account has been banned.");
                }
                Collection<? extends GrantedAuthority> authorities = authService.getAuthorities(email);
                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            } else {
                throw new BadCredentialsException("User not found.");
            }
        } else {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
