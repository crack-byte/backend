package com.tripshare.config;

import com.tripshare.service.UserService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtProcessor jwtProcessor;
    @Autowired
    private UserService userService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        http.cors().configurationSource(this::corsConfiguration).and().csrf().disable().exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setHeader("WWW-Authenticate", authException.getMessage());
            }).accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setHeader("WWW-Authenticate", accessDeniedException.getMessage());
            }).and()
            .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                .antMatchers("/login", "/docs/**", "/swagger-ui/**").permitAll().anyRequest().authenticated())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(new CustomAuthenticationFilter(authenticationManager, jwtProcessor))
            .addFilter(new CustomAuthorizationFilter(authenticationManager, jwtProcessor, userService));
        return http.build();
    }

    private CorsConfiguration corsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.addAllowedOrigin(request.getHeader("Origin"));
        return corsConfig;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .components
                (
                    new Components().addSecuritySchemes("bearerAuth"
                        , new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer").bearerFormat("JWT"))
                );
    }
}
