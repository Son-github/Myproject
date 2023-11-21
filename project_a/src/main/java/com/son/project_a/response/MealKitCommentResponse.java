package com.son.project_a.response;

import com.son.project_a.dto.MealKitCommentDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MealKitCommentResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String userEmail
) implements Serializable {

    public static MealKitCommentResponse of(Long id, String title, String content, LocalDateTime createdAt, String userEmail) {
        return new MealKitCommentResponse(id, title, content, createdAt, userEmail);
    }

    public static MealKitCommentResponse from(MealKitCommentDto dto) {

        return new MealKitCommentResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().userEmail()
        );
    }
}
