package com.son.project_a.repository;

import com.son.project_a.config.JpaConfig;
import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 사용하지 않는 의존성 제거 control+option+o

@DisplayName("jpa 연결 테스트")
@DataJpaTest
public class JpaRepositoryTest {

    private static Logger log = LoggerFactory.getLogger(JpaRepositoryTest.class); // log를 띄우기 위해 사용

    private final UserAccountRepository userAccountRepository;
    private final MealKitRepository mealKitRepository;

    private final MealKitCommentRepository mealKitCommentRepository;

    public JpaRepositoryTest(
            @Autowired UserAccountRepository userAccountRepository,
            @Autowired MealKitRepository mealKitRepository,
            @Autowired MealKitCommentRepository mealKitCommentRepository) {
        this.userAccountRepository = userAccountRepository;
        this.mealKitRepository = mealKitRepository;
        this.mealKitCommentRepository = mealKitCommentRepository;
    }

    @DisplayName("select")
    @Test
    void givenNoting_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccountList = userAccountRepository.findAll(); // 모든 것 찾기
        List<MealKit> mealKitList = mealKitRepository.findAll();

        // Then
        assertThat(userAccountList)
                .isNotNull()
                .hasSize(100);
        assertThat(mealKitList)
                .isNotNull()
                .hasSize(100);
    }

    @DisplayName("insert")
    @Test
    void givenValues_whenInserting_thenWorkFine() {
        // Given
        long prevUserAccount = userAccountRepository.count();

        // When
        UserAccount userAccount = UserAccount.of("key@gmail.com", "test", "key", "port");
        MealKit mealKit = MealKit.of("Test", "3000원", "라볶이", 5, "www.test.co.kr", "black of the son", "www.image.co.kr");
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccountRepository.save(userAccount);
        mealKitRepository.save(mealKit);

        long AfterUserAccount = userAccountRepository.count();



        log.info("날짜 입력됐나요?: {}", userAccount.getCreatedAt());
        log.info("meal_kit에 입력된 자료: {}", mealKitRepository.findById(1L));
        log.info("meal_kit에 입력된 자료의 날짜는? {}", mealKit.getCreatedAt());
        // Then
        assertThat(AfterUserAccount)
                .isEqualTo(prevUserAccount+1);

        assertThat(mealKit.getMName())
                .isEqualTo("Test");
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

    @Test
    void givenUserAccountEmail_when_thenReturnTrue() {
        log.info("ctatem2o@yahoo.com이 존재하나요?: {}",userAccountRepository.existsByUserEmail("ctatem2o@yahoo.com"));
        assertThat(userAccountRepository.existsByUserEmail("ctatem2o@yahoo.com"))
                .isEqualTo(true);
    }
}
