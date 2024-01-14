package com.son.project_a.service;


import com.son.project_a.config.SecurityUtil;
import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.dto.response.UserAccountResponseDto;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountResponseDto getMyInfoWithSecurity() {
        log.info("userAccountRepository.findByUserEmail: {}", userAccountRepository.findByUserEmail(SecurityUtil.getCurrentMemberEmail()));
        return userAccountRepository.findByUserEmail(SecurityUtil.getCurrentMemberEmail())
                .map(UserAccountResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public boolean userCorrect(UserAccountDto dto) {
        return userAccountRepository.existsByUserEmail(dto.userEmail()) && userAccountRepository.existsByUserPassword(dto.userPassword());
    }

}
