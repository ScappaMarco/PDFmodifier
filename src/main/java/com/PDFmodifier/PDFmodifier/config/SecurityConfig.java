package com.PDFmodifier.PDFmodifier.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth.requestMatchers("/", "/register", "/lo0gin", "/css/**", "/js/**").permitAll().anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .oauth2Login(oauth2 -> oauth2.loginPage("/register"));

        return httpSecurity.build();
    }
}