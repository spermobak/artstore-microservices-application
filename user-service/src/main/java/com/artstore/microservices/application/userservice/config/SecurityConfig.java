package com.artstore.microservices.application.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) ->
                        requests.requestMatchers("/**")
                                .access("hasAuthority('SCOPE_message.read')")
                )
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}



