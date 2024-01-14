/*package com.son.project_a.service;

import com.son.project_a.config.SecurityUtil;
import com.son.project_a.dto.response.UserAccountResponseDto;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountResponseDto getMyInfoBySecurity() {
        return userAccountRepository.findByUserEmail(SecurityUtil.getCurrentMemberEmail())
                .map(UserAccountResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 정보가 없습니다."));
    }
}*/
