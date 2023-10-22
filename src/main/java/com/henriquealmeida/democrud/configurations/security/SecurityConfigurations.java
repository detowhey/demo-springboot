package com.henriquealmeida.democrud.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            .requestMatchers(HttpMethod.POST, "/products", "/customer").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/orders", "/products", "/categories").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/customer").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/customer").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/customer").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated()
                    )
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Not authorized", e);
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        try {
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException("Unable to authenticate", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
