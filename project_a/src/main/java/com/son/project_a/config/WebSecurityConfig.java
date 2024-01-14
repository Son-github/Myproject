package com.son.project_a.config;

import com.son.project_a.jwt.JwtAccessDeniedHandler;
import com.son.project_a.jwt.JwtAuthenticationEntryPoint;
import com.son.project_a.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // stateless한 rest api를 개발할 것이므로 csrf 공격에 대한 옵션은 꺼둔다.
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Rest API를 통해 세션없이 토큰을 주고받으며 데이터를 주고받기 때문에 세션을 STATELESS로 설정
                .exceptionHandling( handling -> handling.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeHttpRequests( auth -> auth.requestMatchers("/signin", "/mealKits", "/signup").permitAll().anyRequest().authenticated()) // "/singin", "/mealKits","/signup"를 제외한 모든 uri의 request에는 토큰이 필요하다. TODO: 확인필요
                .formLogin( formLogin -> formLogin.loginPage("/signin"))
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
