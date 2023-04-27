package com.lantana.school.course.coursemanagment.web.secuirty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";
    private final JwtAuthConverter jwtAuthConverter;
    public WebSecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/courses", "/courses/**").hasAnyRole(TEACHER, STUDENT)
                .requestMatchers(HttpMethod.POST, "/courses", "/courses/**").hasRole(TEACHER)
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }
}

