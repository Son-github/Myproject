package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitWithCommentsAndImagesDto(
        Long id,
        String mName,
        int mPrice,
        String mCategory,
        int mStock,
        Set<MealKitSiteDto> mealKitSiteDtos,
        String mContent,
        Set<MealKitImageDto> mealKitImageDtos,
        Set<MealKitCommentDto> mealKitCommentDtos,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MealKitWithCommentsAndImagesDto of(Long id, String mName, int mPrice, String mCategory,
                                                     int mStock, Set<MealKitSiteDto> mealKitSiteDtos, String mContent, Set<MealKitImageDto> mealKitImageDtos,
                                                     Set<MealKitCommentDto> mealKitCommentDtos,
                                                     LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitWithCommentsAndImagesDto(id, mName, mPrice, mCategory, mStock, mealKitSiteDtos, mContent, mealKitImageDtos, mealKitCommentDtos, createdAt, modifiedAt);
    }

    public static MealKitWithCommentsAndImagesDto from(MealKit entity) {
        return new MealKitWithCommentsAndImagesDto(
                entity.getId(),
                entity.getMName(),
                entity.getMPrice(),
                entity.getMCategory(),
                entity.getMStock(),
                entity.getMealKitSites().stream()
                        .map(MealKitSiteDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
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
