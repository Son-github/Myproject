package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;

import java.time.LocalDateTime;

public record MealKitCommentDto(
        Long id,
        Long mealKitId,
        UserAccountDto userAccountDto,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static MealKitCommentDto of (Long mealKitId, UserAccountDto userAccountDto, String title, String content) {
        return new MealKitCommentDto(null, mealKitId, userAccountDto, title, content, null, null);
    }
    public static MealKitCommentDto of(Long id, Long mealKitId, UserAccountDto userAccountDto, String title,
                                       String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitCommentDto(id, mealKitId, userAccountDto, title, content, createdAt, modifiedAt);
    }

    public static MealKitCommentDto from(MealKitComment entity) {
        return new MealKitCommentDto(
                entity.getId(),
                entity.getMealKit().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getTitle(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public MealKitComment toEntity(MealKit mealKit, UserAccount userAccount) {
        return MealKitComment.of(
                mealKit,
                userAccount,
                title,
                content
        );
    }
}
