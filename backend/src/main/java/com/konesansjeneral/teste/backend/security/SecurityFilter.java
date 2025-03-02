package com.konesansjeneral.teste.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter {

    public static final String ADMIN_ROLE = "ADMIN";

    @Autowired
    private SuccessLoginHandler successLoginHandler;

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/quizzes", "/quizzes/*", "/token/refresh", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users", "/quizzes/export").hasAnyAuthority(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.POST, "/quizzes").hasAnyAuthority(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.PUT, "/quizzes/*").hasAnyAuthority(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/quizzes/*").hasAnyAuthority(ADMIN_ROLE)
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Login -> oauth2Login.successHandler(successLoginHandler))
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable) // As we use Token Authentication
                .build();
    }

}
