package com.son.project_a.dto.request;

import com.son.project_a.dto.MealKitCommentDto;
import com.son.project_a.dto.UserAccountDto;

public record MealKitCommentRequest(
        Long mealKitId,
        String title,
        String content
) {

    public static MealKitCommentRequest of(Long mealKitId, String title, String content) {
        return new MealKitCommentRequest(mealKitId, title, content);
    }

    public MealKitCommentDto toDto(UserAccountDto userAccountDto) {
        return MealKitCommentDto.of(
                mealKitId,
                userAccountDto,
                title,
                content
        );
    }
}
