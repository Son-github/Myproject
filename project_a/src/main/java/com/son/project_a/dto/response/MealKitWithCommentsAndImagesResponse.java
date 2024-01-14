package com.son.project_a.dto.response;

import com.son.project_a.dto.MealKitWithCommentsAndImagesDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitWithCommentsAndImagesResponse(
        Long id,
        String mName,
        int mPrice,
        String mCategory,
        Set<MealKitSiteResponse> mealKitSiteResponses,
        String mContent,
        Set<MealKitImageResponse> mealKitImageResponses,
        Set<MealKitCommentResponse> mealKitCommentResponses,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) implements Serializable {

    public static MealKitWithCommentsAndImagesResponse of(Long id, String mName, int mPrice, String mCategory,
                                                 Set<MealKitSiteResponse> mealKitSiteResponses, String mContent, Set<MealKitImageResponse> mealKitImageResponses,
                                                 Set<MealKitCommentResponse> mealKitCommentResponses,
                                                 LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MealKitWithCommentsAndImagesResponse(id, mName, mPrice, mCategory, mealKitSiteResponses, mContent, mealKitImageResponses, mealKitCommentResponses, createdAt, modifiedAt);
    }

    public static MealKitWithCommentsAndImagesResponse from(MealKitWithCommentsAndImagesDto dto) {
        return new MealKitWithCommentsAndImagesResponse(
                dto.id(),
                dto.mName(),
                dto.mPrice(),
                dto.mCategory(),
                dto.mealKitSiteDtos()
                        .stream()
                        .map(MealKitSiteResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                dto.mContent(),
                dto.mealKitImageDtos()
                        .stream()
                        .map(MealKitImageResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                dto.mealKitCommentDtos()
                        .stream()
                        .map(MealKitCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
