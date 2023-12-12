package com.son.project_a.dto;

import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.MealKitCommentImage;

import java.time.LocalDateTime;

public record MealKitCommentImageDto(
        Long id,

        MealKitComment mealKitComment,
        String commentImageUrl,
        LocalDateTime createdAt
) {

    public static MealKitCommentImageDto of(Long id, MealKitComment mealKitComment, String commentImageUrl, LocalDateTime createdAt) {
        return new MealKitCommentImageDto(id, mealKitComment, commentImageUrl, createdAt);
    }

    public static MealKitCommentImageDto from(MealKitCommentImage entity) {
        return new MealKitCommentImageDto(
                entity.getId(),
                entity.getMealKitComment(),
                entity.getCommentImageUrl(),
                entity.getCreatedAt()
        );
    }

    public MealKitCommentImage toEntity(MealKitComment entity) {
        return MealKitCommentImage.of(
                entity,
                commentImageUrl
        );
    }
}
