package com.son.project_a.repository;

import com.son.project_a.config.JpaConfig;
import com.son.project_a.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 사용하지 않는 의존성 제거 control+option+o

@DisplayName("jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

    private static Logger log = LoggerFactory.getLogger(JpaRepositoryTest.class); // log를 띄우기 위해 사용

    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select")
    @Test
    void givenNoting_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccountList = userAccountRepository.findAll(); // 모든 것 찾기

        // Then
        assertThat(userAccountList)
                .isNotNull()
                .hasSize(100);
    }

    @DisplayName("insert")
    @Test
    void givenValues_whenInserting_thenWorkFine() {
        // Given
        long prevUserAccount = userAccountRepository.count();

        // When
        userAccountRepository.save(UserAccount.of("key@gmail.com", "test", "key", "port"));
        long AfterUserAccount = userAccountRepository.count();

        // Then
        assertThat(AfterUserAccount)
                .isEqualTo(prevUserAccount+1);
    }

    @DisplayName("update")
    @Test
    void givenUpdateValue_whenUpdate_thenWorkFine() {
        // Given
        UserAccount userAccount = userAccountRepository.findById(1L).orElseThrow();
        String updateEmail = "jongin718@gmail.com";
        userAccount.setUserEmail(updateEmail);

        // When
        UserAccount updateUserAccount = userAccountRepository.saveAndFlush(userAccount); // save와 saveAndFlush의 차이는?

        // Then
        assertThat(updateUserAccount)
                .hasFieldOrPropertyWithValue("userEmail", updateEmail);
    }

    @DisplayName("delete")
    @Test
    void given_when_then() {
        // Given
        UserAccount userAccount = userAccountRepository.findById(1L).orElseThrow();
        long preciousUserAccountCount = userAccountRepository.count();

        // When
        userAccountRepository.delete(userAccount);

        // Then
        assertThat(userAccountRepository.count())
                .isEqualTo(preciousUserAccountCount-1);
    }
}
