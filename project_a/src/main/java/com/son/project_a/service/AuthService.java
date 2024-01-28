package com.son.project_a.service;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.request.UserAccountRequest;
import com.son.project_a.dto.response.UserAccountResponse;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserAccountResponse signup(UserAccountRequest requestDto) {
        var user = UserAccount.of(
                requestDto.getUserEmail(),
                passwordEncoder.encode(requestDto.getUserPassword()),
                requestDto.getFirstname(),
                requestDto.getLastname(),
                requestDto.getNickname());
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return UserAccountResponse.builder()
                .userEmail(requestDto.getUserEmail())
                .token(jwtToken)
                .build();
    }

    public UserAccountResponse signIn(UserAccountRequest requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUserEmail(),
                        requestDto.getUserPassword()
                )
        );
        var user = repository.findByUserEmail(requestDto.getUserEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return UserAccountResponse.builder()
                .userEmail(requestDto.getUserEmail())
                .token(jwtToken)
                .build();
    }

}
