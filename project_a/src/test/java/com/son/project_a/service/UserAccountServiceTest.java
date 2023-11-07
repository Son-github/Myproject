package com.son.project_a.service;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class UserAccountServiceTest {

    private static Logger log = LoggerFactory.getLogger(UserAccountServiceTest.class);


    private final UserAccountService userAccountService;
    private final UserAccountRepository userAccountRepository;
    private final UserAccount userAccount;

    UserAccountServiceTest(@Autowired UserAccountService userAccountService, @Autowired UserAccountRepository userAccountRepository, @Autowired UserAccount userAccount) {
        this.userAccountService = userAccountService;
        this.userAccountRepository = userAccountRepository;
        this.userAccount = userAccount;
    }

    /*@DisplayName("회원가입")
    @Test
    void givenUserAccount_whenSingUp_thenSaveUserAccount() {
        // Given
        long prevUserAccount = userAccountRepository.count();
        UserAccount userAccount =  new UserAccount("son", "jongin", "jongin718@gmail.com", "test");

        // When
        userAccountService.saveUserAccount(userAccount);
        long afterUserAccount = userAccountRepository.count();

        // Then
        assertThat(afterUserAccount)
                .isEqualTo(prevUserAccount+1);
    }*/

}