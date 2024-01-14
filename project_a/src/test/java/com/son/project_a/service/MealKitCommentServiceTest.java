package com.son.project_a.service;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.MealKitCommentDto;
import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.repository.MealKitCommentRepository;
import com.son.project_a.repository.MealKitRepository;
import com.son.project_a.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;

@DisplayName("밀키트 댓글")
@ExtendWith(MockitoExtension.class)
class MealKitCommentServiceTest {

    Logger log = LoggerFactory.getLogger(MealKitCommentServiceTest.class);

    @InjectMocks private MealKitCommentService mealKitCommentService;

    @Mock private MealKitCommentRepository mealKitCommentRepository;
    @Mock private MealKitRepository mealKitRepository;
    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("밀키트 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenMealKitId_whenSearchComments_thenReturnListOfComments() {
        // Given
        long mealKitId = 1L;
        MealKitComment mealKitComment = createMealKitComment(1L, "Hello", "Test Content");
        given(mealKitCommentRepository.findByMealKitId(mealKitId)).willReturn(List.of(
                mealKitComment
        ));

        // When
        List<MealKitCommentDto> mealKitComments = mealKitCommentService.searchMealKitComment(mealKitId);

        log.info("mealKitComments에는 뭐가 있을까요? {}" , mealKitComments);
        // Then
        assertThat(mealKitComments).hasSize(1);
        assertThat(mealKitComments)
                .extracting("id", "mealKitId", "title", "content")
                .containsExactlyInAnyOrder(
                        tuple(1L, 1L, "Test Content" , "Hello")
                );
        then(mealKitCommentRepository).should().findByMealKitId(mealKitId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenCommentInfo_whenInsertingComments_thenSaveComment() {
        // Given
        MealKitCommentDto dto = createMealKitCommentDto("제목", "댓글");
        given(mealKitRepository.getReferenceById(dto.mealKitId())).willReturn(createMealKit());
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userEmail())).willReturn(createUserAccount());
        given(mealKitCommentRepository.save(any(MealKitComment.class))).willReturn(null);

        // When
        mealKitCommentService.saveMealKitComment(dto);

        // Then
        then(mealKitRepository).should().getReferenceById(dto.mealKitId());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userEmail());
        then(mealKitCommentRepository).should(never()).getReferenceById(anyLong());
        then(mealKitCommentRepository).should().save(any(MealKitComment.class));
    }

    @DisplayName("댓글 저장을 시도하는데 맞는 게시글이 없으면 결과 로그를 찍고 아무것도 안한다.")
    @Test
    void givenNonExistingComment_whenSavingComment_thenLogSituationAndDoNothing(){
        // Given
        MealKitCommentDto dto = createMealKitCommentDto("제목", "댓글");
        given(mealKitRepository.getReferenceById(dto.mealKitId())).willThrow(EntityNotFoundException.class);

        // When
        mealKitCommentService.saveMealKitComment(dto);

        // Then
        then(mealKitRepository).should().getReferenceById(dto.mealKitId());
        then(userAccountRepository).shouldHaveNoInteractions();
        then(mealKitCommentRepository).shouldHaveNoInteractions();
    }

    @DisplayName("없는 댓글 정보를 수정하려고 하면, 경고 로그를 찍고 아무 것도 안한다. ")
    @Test
    void givenNonExistMealKitComment_whenUpdatingMealKitComment_thenLogsWarningAndDoesNothing() {
        // Given
        MealKitCommentDto dto = createMealKitCommentDto("제목", "댓글");
        given(mealKitCommentRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        // When
        mealKitCommentService.updateMealKitComment(dto);

        // Then
        then(mealKitCommentRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenMealKitCommentId_whenDeletingMealKitComment_thenDeletesMealKitComment() {
        // Given
        Long mealKitCommentId = 1L;
        String userEmail = "son@test.com";
        willDoNothing().given(mealKitCommentRepository).deleteByIdAndUserAccount_UserEmail(mealKitCommentId, userEmail);

        // When
        mealKitCommentService.deleteMealKitComment(mealKitCommentId, userEmail);

        // Then
        then(mealKitCommentRepository).should().deleteByIdAndUserAccount_UserEmail(mealKitCommentId, userEmail);
    }

    private MealKitCommentDto createMealKitCommentDto(String title, String content) {
        return createMealKitCommentDto(1L, title, content);
    }

    private MealKitCommentDto createMealKitCommentDto(Long id, String title, String content) {
        return MealKitCommentDto.of(
                id,
                createUserAccountDto(),
                title,
                content
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "jongin718@test.com",
                "1111",
                "son",
                "jongin",
                "nickname"
        );
    }

    private MealKitComment createMealKitComment(Long id, String title, String content) {
        MealKitComment mealKitComment = MealKitComment.of(
                createMealKit(),
                createUserAccount(),
                title,
                content
        );
        ReflectionTestUtils.setField(mealKitComment, "id", id);

        return mealKitComment;
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "jongin718@test.com",
                "1234",
                "son",
                "jongin",
                "nicename"
        );
    }

    private MealKit createMealKit() {
        MealKit mealKit = MealKit.of(
                "떡볶이",
                3000,
                "밀가루",
                "설명했으요",
                5,
                "판매회사",
                500
        );
        ReflectionTestUtils.setField(mealKit, "id", 1L);

        return mealKit;
    }
}