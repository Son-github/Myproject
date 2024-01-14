package com.son.project_a.repository;

import com.son.project_a.domain.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    private final MealKitImageRepository mealKitImageRepository;
    private final MealKitCommentImageRepository mealKitCommentImageRepository;

    public JpaRepositoryTest(
            @Autowired UserAccountRepository userAccountRepository,
            @Autowired MealKitRepository mealKitRepository,
            @Autowired MealKitCommentRepository mealKitCommentRepository,
            @Autowired MealKitImageRepository mealKitImageRepository,
            @Autowired MealKitCommentImageRepository mealKitCommentImageRepository) {
        this.userAccountRepository = userAccountRepository;
        this.mealKitRepository = mealKitRepository;
        this.mealKitCommentRepository = mealKitCommentRepository;
        this.mealKitImageRepository = mealKitImageRepository;
        this.mealKitCommentImageRepository = mealKitCommentImageRepository;
    }

    @DisplayName("select")
    @Test
    void givenNoting_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccountList = userAccountRepository.findAll(); // 모든 것 찾기
        List<MealKit> mealKitList = mealKitRepository.findAll();
        List<MealKitComment> mealKitCommentList = mealKitCommentRepository.findAll();
        List<MealKitImage> mealKitImagesList = mealKitImageRepository.findAll();
        List<MealKitCommentImage> mealKitCommentImagesList = mealKitCommentImageRepository.findAll();

        // Then
        assertThat(userAccountList)
                .isNotNull()
                .hasSize(100);
        assertThat(mealKitList)
                .isNotNull()
                .hasSize(100);
        assertThat(mealKitImagesList)
                .isNotNull()
                .hasSize(100);
        assertThat(mealKitCommentList)
                .isNotNull()
                .hasSize(100);
        assertThat(mealKitCommentImagesList)
                .isNotNull()
                .hasSize(100);

    }

    @DisplayName("insert")
    @Test
    void givenValues_whenInserting_thenWorkFine() {
        // Given

        // When


        // Then
    }


    @DisplayName("update")
    @Test
    void givenUpdateValue_whenUpdate_thenWorkFine() {
        // Given
        UserAccount userAccount = userAccountRepository.findById("userEmail").orElseThrow();
        String updateEmail = "jongin718@gmail.com";
        userAccount.setUserEmail(updateEmail);

        // When
        UserAccount updateUserAccount = userAccountRepository.saveAndFlush(userAccount); // save와 saveAndFlush의 차이는?

        // Then
        assertThat(updateUserAccount)
                .hasFieldOrPropertyWithValue("userEmail", updateEmail);
    }


    @Disabled
    @DisplayName("delete")
    @Test
    void given_when_then() {
        // Given
        UserAccount userAccount = userAccountRepository.findById("userEmail").orElseThrow();
        long preciousUserAccountCount = userAccountRepository.count();

        // When
        userAccountRepository.delete(userAccount);

        // Then
        assertThat(userAccountRepository.count())
                .isEqualTo(preciousUserAccountCount-1);
    }




}

