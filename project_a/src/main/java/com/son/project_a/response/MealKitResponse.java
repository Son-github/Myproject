package com.son.project_a.response;

import com.son.project_a.domain.MealKitImage;
import com.son.project_a.dto.MealKitDto;
import com.son.project_a.dto.MealKitImageDto;
import com.son.project_a.dto.MealKitSiteDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitResponse(
        Long id,
        String mName,
        int mPrice,
        String mCategory,
        Set<String> mealKitSites,
        Set<String> mealKitImages
) implements Serializable {

    public static MealKitResponse of(Long id, String mName, int mPrice, String mCategory, Set<String> mealKitSites,
                                     Set<String> mealKitImages) {
        return new MealKitResponse(id, mName, mPrice, mCategory, mealKitSites, mealKitImages);
    }

    public static MealKitResponse from(MealKitDto dto) {
        return new MealKitResponse(
                dto.id(),
                dto.mName(),
                dto.mPrice(),
                dto.mCategory(),
                dto.mealKitSiteDtos().stream()
                        .map(MealKitSiteDto::siteUrl)
                        .collect(Collectors.toUnmodifiableSet()),
                dto.mealKitImageDtos().stream()
                        .map(MealKitImageDto::imageUrl)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }
}
