package com.son.project_a.dto.response;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DTO - 댓글을 포함한 게시글 응답 테스트")
class MealKitWithCommentsAndImagesResponseTest {

    static Logger log = LoggerFactory.getLogger(MealKitWithCommentsAndImagesResponseTest.class);

    @DisplayName("자식 댓글이 없는 게시글 + 댓글 dto를 api 응답으로 변환할 때, 댓글을 시간 내림차순 + ID 오름차순으로 정리한다.")
    @Test
    void givenMealKitWithCommentWithoutChildComments_whenMapping_thenOrganizesCommentsWithCertainOrder() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        Set<MealKitCommentDto> mealKitCommentDtos = Set.of(
                createMealKitCommentDto(1L, now),
                createMealKitCommentDto(2L, now.plusDays(1L)),
                createMealKitCommentDto(3L, now.plusDays(2L)),
                createMealKitCommentDto(4L, now.plusDays(3L)),
                createMealKitCommentDto(5L, now.plusDays(4L)),
                createMealKitCommentDto(6L, now.plusDays(5L)),
                createMealKitCommentDto(7L, now.plusDays(6L)),
                createMealKitCommentDto(8L, now.plusDays(7L))
        );

        Set<MealKitImageDto> mealKitImageDtos = Set.of(
                createMealKitImageDto(1L, "imageUrl"),
                createMealKitImageDto(2L, "imageUrl"),
                createMealKitImageDto(3L, "imageUrl"),
                createMealKitImageDto(4L, "imageUrl"),
                createMealKitImageDto(5L, "imageUrl"),
                createMealKitImageDto(6L, "imageUrl"),
                createMealKitImageDto(7L, "imageUrl"),
                createMealKitImageDto(8L, "imageUrl")
        );

        Set<MealKitSiteDto> mealKitSiteDtos = Set.of(
                createMealKitSiteDto(1L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(2L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(3L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(4L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(5L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(6L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(7L, "siteName", 5000, "siteUrl"),
                createMealKitSiteDto(8L, "siteName", 5000, "siteUrl")
        );

        MealKitWithCommentsAndImagesDto input = createMealKitWithCommentAndImageDto(mealKitSiteDtos, mealKitCommentDtos, mealKitImageDtos);

        // When
        MealKitWithCommentsAndImagesResponse actual = MealKitWithCommentsAndImagesResponse.from(input);

        // Then
        Iterator<MealKitCommentResponse> iterator = actual.mealKitCommentResponses().iterator();
        while (iterator.hasNext()) {
            MealKitCommentResponse mealKitCommentResponse = iterator.next();
            log.info("Comment: {}", mealKitCommentResponse.id());
        }
    }

    private MealKitWithCommentsAndImagesDto createMealKitWithCommentAndImageDto(Set<MealKitSiteDto> mealKitSiteDtos, Set<MealKitCommentDto> mealKitCommentDtos, Set<MealKitImageDto> mealKitImageDtos) {
        return MealKitWithCommentsAndImagesDto.of(
                1L,
                "mName",
                5000,
                "category",
                mealKitSiteDtos,
                "content",
                mealKitImageDtos,
                mealKitCommentDtos,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "userEmail",
                "password",
                "firstName",
                "lastName",
                "nickname",
                LocalDateTime.now()
        );
    }

    private MealKitCommentDto createMealKitCommentDto(Long id, LocalDateTime createAt) {
        return MealKitCommentDto.of(
                id,
                1L,
                createUserAccountDto(),
                "title",
                "content",
                createAt,
                createAt
        );
    }

    private MealKitSiteDto createMealKitSiteDto(Long id, String siteName, int sitePrice, String siteUrl) {
        return MealKitSiteDto.of(
                id,
                siteName,
                sitePrice,
                siteUrl
        );
    }

    private MealKitImageDto createMealKitImageDto(Long id, String imageUrl) {
        return MealKitImageDto.of(
                id,
                imageUrl
        );
    }

    private MealKitCommentResponse createMealKitCommentResponse(Long id, LocalDateTime createAt) {
        return MealKitCommentResponse.of(
                id,
                "title",
                "content",
                createAt,
                "userEmail",
                "nickname"
        );
    }

}