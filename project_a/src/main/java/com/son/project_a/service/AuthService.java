package com.son.project_a.service;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.dto.jwt.TokenDto;
import com.son.project_a.dto.request.UserAccountRequestDto;
import com.son.project_a.dto.response.UserAccountResponseDto;
import com.son.project_a.jwt.TokenProvider;
import com.son.project_a.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    Logger log = LoggerFactory.getLogger(AuthService.class);
    private final AuthenticationManagerBuilder managerBuilder;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(AuthenticationManagerBuilder managerBuilder, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.managerBuilder = managerBuilder;
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public UserAccountResponseDto signup(UserAccountRequestDto requestDto) {
        if (userAccountRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw  new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        UserAccount userAccount = requestDto.toUserAccount(passwordEncoder);
        return UserAccountResponseDto.of(userAccountRepository.save(userAccount));
    }

    public TokenDto signin(UserAccountRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }
}
