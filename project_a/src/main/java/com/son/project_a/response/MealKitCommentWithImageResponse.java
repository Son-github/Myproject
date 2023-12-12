package com.son.project_a.response;

import com.son.project_a.dto.MealKitCommentImageDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MealKitCommentWithImageResponse(
        Long id,
        String commentImageUrl,
        LocalDateTime createdAt
) implements Serializable {

    public static MealKitCommentWithImageResponse of(Long id, String commentImageUrl, LocalDateTime createdAt) {
        return new MealKitCommentWithImageResponse(id, commentImageUrl, createdAt);
    }

    public static MealKitCommentWithImageResponse from(MealKitCommentImageDto dto) {

        return new MealKitCommentWithImageResponse(
                dto.id(),
                dto.commentImageUrl(),
                dto.createdAt()
        );
    }
}
