package com.son.project_a.service;


import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }


    public boolean userCorrect(UserAccountDto dto) {
        return userAccountRepository.existsByUserEmail(dto.userEmail()) && userAccountRepository.existsByUserPassword(dto.userPassword());
    }

}
