package com.example.neksus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/register", "/login", "/", "/news", "/games/**", "/mods/**", "/modDetails/**", "/css/**", "/img/**", "/js/**").permitAll()
                .requestMatchers("/dashboard").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, CustomAuthenticationProvider customAuthenticationProvider) {
        auth.authenticationProvider(customAuthenticationProvider);
//        All the code below is already implemented outside of this class
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder)
//                .usersByUsernameQuery("SELECT email, password FROM n_user WHERE email=?")
//                .authoritiesByUsernameQuery("SELECT email, (CASE WHEN is_admin='Y' THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END) AS authority FROM n_user WHERE email=?");
    }
}
