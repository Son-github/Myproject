package com.son.project_a.service;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.MealKitCommentDto;
import com.son.project_a.repository.MealKitCommentRepository;
import com.son.project_a.repository.MealKitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("밀키트 댓글")
@ExtendWith(MockitoExtension.class)
class MealKitCommentServiceTest {

    Logger log = LoggerFactory.getLogger(MealKitCommentServiceTest.class);

    @InjectMocks private MealKitCommentService mealKitCommentService;

    @Mock private MealKitCommentRepository mealKitCommentRepository;
    @Mock private MealKitRepository mealKitRepository;

    @DisplayName("밀키트 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenMealKitId_whenSearchComments_thenReturnListOfComments() {
        // Given
        long mealKitId = 1L;
        given(mealKitRepository.findById(mealKitId)).willReturn(Optional.of(
                MealKit.of("test", "3,400", "떡볶이", 5, "www.naver.com","This food is very healthy food!!")
        ));

        // When
        List<MealKitCommentDto> mealKitComments = mealKitCommentService.searchMealKitComment(1L);

        log.info("mealKitComments에는 뭐가 있을까요? {}" , mealKitComments);
        // Then

    }
}