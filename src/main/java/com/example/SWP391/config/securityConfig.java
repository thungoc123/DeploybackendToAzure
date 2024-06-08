package com.example.SWP391.config;

import com.example.SWP391.Security.CustomeAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomeAuthenProvider customeauthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customeauthenProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(author -> {
                    author.requestMatchers("/api-auth/**").permitAll();// tu do ko can dang nhap
//                    author.requestMatchers(HttpMethod.GET,"/product").permitAll();// tu do ko can dang nhap
                    author.requestMatchers("/api/**").permitAll();
                    author.requestMatchers("/api-sponsor/**").permitAll();
                    author.requestMatchers("/api-events/**").permitAll();
                    author.anyRequest().authenticated(); // tat ca link con lai phai chung thuc
                }).build();
    }
}
