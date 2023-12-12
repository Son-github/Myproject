package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitWithCommentsAndImagesDto(
        Long id,
        String mName,
        String mPrice,
        String mCategory,
        int mStock,
        String mSite,
        String mContent,
        Set<MealKitImageDto> mealKitImageDtos,
        Set<MealKitCommentDto> mealKitCommentDtos,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MealKitWithCommentsAndImagesDto of(Long id, String mName, String mPrice, String mCategory,
                                                     int mStock, String mSite, String mContent, Set<MealKitImageDto> mealKitImageDtos,
                                                     Set<MealKitCommentDto> mealKitCommentDtos,
                                                     LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitWithCommentsAndImagesDto(id, mName, mPrice, mCategory, mStock, mSite, mContent, mealKitImageDtos, mealKitCommentDtos, createdAt, modifiedAt);
    }

    public static MealKitWithCommentsAndImagesDto from(MealKit entity) {
        return new MealKitWithCommentsAndImagesDto(
                entity.getId(),
                entity.getMName(),
                entity.getMPrice(),
                entity.getMCategory(),
                entity.getMStock(),
                entity.getMSite(),
                entity.getMContent(),
                entity.getMealKitImages().stream()
                        .map(MealKitImageDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getMealKitComments().stream()
                        .map(MealKitCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
