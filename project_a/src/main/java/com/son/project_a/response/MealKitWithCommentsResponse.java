package com.son.project_a.response;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.MealKitCommentDto;
import com.son.project_a.dto.MealKitWithCommentsDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitWithCommentsResponse(
        Long id,
        String mName,
        String mPrice,
        String mCategory,
        int mStock,
        String mSite,
        String mContent,
        String mImage,
        Set<MealKitCommentResponse> mealKitCommentResponses,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) implements Serializable {

    public static MealKitWithCommentsResponse of(Long id, String mName, String mPrice, String mCategory,
                                                 int mStock, String mSite, String mContent, String mImage,
                                                 Set<MealKitCommentResponse> mealKitCommentResponses,
                                                 LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitWithCommentsResponse(id, mName, mPrice, mCategory, mStock, mSite, mContent, mImage, mealKitCommentResponses, createdAt, modifiedAt);
    }

    public static MealKitWithCommentsResponse from(MealKitWithCommentsDto dto) {
        return new MealKitWithCommentsResponse(
                dto.id(),
                dto.mName(),
                dto.mPrice(),
                dto.mCategory(),
                dto.mStock(),
                dto.mSite(),
                dto.mContent(),
                dto.mImage(),
                dto.mealKitCommentDtos().stream().map(MealKitCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
