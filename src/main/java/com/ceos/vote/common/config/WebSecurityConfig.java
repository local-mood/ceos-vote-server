package com.ceos.vote.common.config;

import com.ceos.vote.auth.exception.JwtAuthenticationEntryPoint;
import com.ceos.vote.auth.jwt.filter.JwtAuthenticationFilter;
import com.ceos.vote.auth.jwt.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ceos.vote.auth.exception.JwtAccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final CorsConfig corsConfig;

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
          .httpBasic(HttpBasicConfigurer::disable)
          .csrf(CsrfConfigurer::disable)
          .formLogin(FormLoginConfigurer::disable);

        http
          .addFilter(corsConfig.corsFilter())
          .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
          .exceptionHandling((exceptionHandling) ->
            exceptionHandling
              .authenticationEntryPoint(jwtAuthenticationEntryPoint)
              .accessDeniedHandler(jwtAccessDeniedHandler)
          );

        http
          .authorizeRequests() //
          .requestMatchers("/", "/app/auth/signup", "/app/auth/login/**", "/app/auth/login")
          .permitAll()
          .requestMatchers("/app/member", "/app/member/**/vote")
          .permitAll()
          .requestMatchers("/app/team", "/app/team/**/vote")
          .permitAll()
          .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()
              .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-vote.html"
              );
        };
    }

}