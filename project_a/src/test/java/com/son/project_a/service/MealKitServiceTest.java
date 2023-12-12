package com.son.project_a.service;

import com.son.project_a.domain.MealKit;
import com.son.project_a.dto.MealKitWithCommentsAndImagesDto;
import com.son.project_a.repository.MealKitRepository;
import jakarta.persistence.EntityNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("MealKit 도메인과 관련된 test")
@ExtendWith(MockitoExtension.class)
class MealKitServiceTest {

    Logger log = LoggerFactory.getLogger(MealKitServiceTest.class);

    @InjectMocks private MealKitService mealKitService;
    @Mock private MealKitRepository mealKitRepository;

    @DisplayName("검색어 없이 게시글을 검색하면, 게시글을 반환")
    @Test
    void givenNothing_WhenSearchMealKit_thenReturnsMealKitPage() {
        // Given
        List<MealKit> mealKits = mealKitRepository.findAll();

        // When
        log.info("mealKits: {}" , mealKits);


        // Then

    }

    /*@Disabled
    @DisplayName("검색어를 준다면, 게시글 페이지를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingMealKit_thenReturnsMealKitPage() {
        // Given
        SearchType searchType = SearchType.MNAME;
        String searchKeyword = "mName";
        Pageable pageable = Pageable.ofSize(20);
        given(mealKitRepository.findByMName(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<MealKitDto> mealKits = mealKitService.searchMealKits(searchType, searchKeyword, pageable);

        // Then
        assertThat(mealKits).isEmpty();
        then(mealKitRepository).should().findByMName(searchKeyword, pageable);
    }*/

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenMealKitId_whenSearchingMealKit_thenReturnMealKit() {
        // Given
        Long mealKitId = 1L;
        MealKit mealKit = createMealKit();
        given(mealKitRepository.findById(mealKitId)).willReturn(Optional.of(mealKit));

        // When
        MealKitWithCommentsAndImagesDto dto = mealKitService.getMealKit(mealKitId);

        // Then
        // log.info("뭐인가요? {}", mealKit.getMName());
        assertThat(dto)
                .hasFieldOrPropertyWithValue("mName", mealKit.getMName());
        then(mealKitRepository).should().findById(mealKitId);
    }

    @DisplayName("없는 게시글을 조회하면, 예외를 던진다.")
    @Test
    void givenNonExistingMealKit_whenSearchingMealKit_thenThrowsExcept() {
        // Given
        long mealKitId = 0L;
        given(mealKitRepository.findById(mealKitId)).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> mealKitService.getMealKit(mealKitId));

        // Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("MealKit가 없습니다.");
        then(mealKitRepository).should().findById(mealKitId);
    }

    private MealKit createMealKit() {
        return MealKit.of(
                "Test",
                "3000원",
                "떡볶이",
                32,
                "www.naver.com",
                "이 떡볶이는 제일 맛있어요!"
        );
    }

}