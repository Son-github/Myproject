package com.son.project_a.dto.response;

import com.son.project_a.dto.MealKitCommentDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MealKitCommentResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String userEmail,
        String nickName
) implements Serializable {

    public static MealKitCommentResponse of(Long id, String title, String content, LocalDateTime createdAt, String userEmail, String nickName) {
        return new MealKitCommentResponse(id, title, content, createdAt, userEmail, nickName);
    }

    public static MealKitCommentResponse from(MealKitCommentDto dto) {
        String nickName = dto.userAccountDto().nickName();
        if (nickName == null || nickName.isBlank()) {
            nickName = dto.userAccountDto().lastName();
        }

        return new MealKitCommentResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().nickName()
        );
    }
}
