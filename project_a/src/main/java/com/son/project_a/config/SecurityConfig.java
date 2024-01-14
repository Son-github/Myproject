package com.son.project_a.config;

import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.dto.security.MealKitBoardPrincipal;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity // Spring Security 필터가 스프링 필터체인에 등록이 됨.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // stateless한 rest api를 개발할 것이므로 csrf 공격에 대한 옵션은 꺼둔다.
                .csrf(AbstractHttpConfigurer::disable)

                // 특정 URL에 대한 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin", "/signup").permitAll().anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService (UserAccountRepository userAccountRepository) {
        return userEmail -> userAccountRepository
                .findById(userEmail)
                .map(UserAccountDto::from)
                .map(MealKitBoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException(("유저를 찾을 수 없습니다 - userEmail " + userEmail)));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
