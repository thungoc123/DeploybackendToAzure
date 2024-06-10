package com.example.SWP391.config;

import com.example.SWP391.filter.CustomFilterSecurity;
import com.example.SWP391.security.CustomeAuthenProvider;
import org.apache.catalina.filters.CorsFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class securityConfig{

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomeAuthenProvider customeauthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customeauthenProvider)
                .build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, CustomFilterSecurity customFilterSecurity) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(author -> {
//                    author.requestMatchers("/api-auth/**").permitAll();// tu do ko can dang nhap
//                    author.requestMatchers(HttpMethod.GET,"/product").permitAll();// tu do ko can dang nhap
//                    author.requestMatchers("/api/**").permitAll();
//                    author.requestMatchers("/api-sponsor/**").permitAll();
//                    author.requestMatchers("/api-events/**").permitAll();
//                    author.anyRequest().authenticated(); // tat ca link con lai phai chung thuc
//                })
//                .addFilterBefore(customFilterSecurity, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http, CustomFilterSecurity customFilterSecurity) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeRequests(authorize -> authorize
                    .requestMatchers("/api-auth/**").permitAll() // Allow access to /api-auth/** without authentication
                    .requestMatchers(HttpMethod.GET, "/product").permitAll() // Allow GET requests to /product without authentication
                    .requestMatchers("/api/**").permitAll() // Allow access to /api/** without authentication
                    .requestMatchers("/api-sponsor/**").permitAll() // Allow access to /api-sponsor/** without authentication
                    .requestMatchers("/api-events/**").permitAll() // Allow access to /api-events/** without authentication
                    .anyRequest().authenticated() // Require authentication for any other request
            )
            .addFilterBefore(customFilterSecurity, UsernamePasswordAuthenticationFilter.class)
            .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Enable CORS with custom configuration

    return http.build();
}

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://eventmanagementfu.azurewebsites.net/")); // Adjust allowed origins
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
