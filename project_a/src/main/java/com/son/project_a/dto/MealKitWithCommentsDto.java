package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitWithCommentsDto(
        Long id,
        String mName,
        String mPrice,
        String mCategory,
        int mStock,
        String mSite,
        String mContent,
        String mImage,
        Set<MealKitCommentDto> mealKitCommentDtos,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MealKitWithCommentsDto of(Long id, String mName, String mPrice, String mCategory,
                                            int mStock, String mSite, String mContent, String mImage,
                                            Set<MealKitCommentDto> mealKitCommentDtos,
                                            LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitWithCommentsDto(id, mName, mPrice, mCategory, mStock, mSite, mContent, mImage, mealKitCommentDtos, createdAt, modifiedAt);
    }

    public static MealKitWithCommentsDto from(MealKit entity) {
        return new MealKitWithCommentsDto(
                entity.getId(),
                entity.getMName(),
                entity.getMPrice(),
                entity.getMCategory(),
                entity.getMStock(),
                entity.getMSite(),
                entity.getMContent(),
                entity.getMImage(),
                entity.getMealKitComments().stream()
                        .map(MealKitCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
