package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;

import java.time.LocalDateTime;

public record MealKitCommentDto(
        Long id,
        String title,
        Long mealKitId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MealKitCommentDto of(Long id, String title, Long mealKitId, UserAccountDto userAccountDto,
                                       String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitCommentDto(id, title, mealKitId, userAccountDto, content, createdAt, modifiedAt);
    }

    public static MealKitCommentDto from(MealKitComment entity) {
        return new MealKitCommentDto(
                entity.getId(),
                entity.getTitle(),
                entity.getMealKit().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public MealKitComment toEntity(MealKit entity) {
        return MealKitComment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );
    }
}
