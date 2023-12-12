package com.son.project_a.dto;

import com.son.project_a.domain.MealKitImage;

public record MealKitImageDto(
        Long id,
        String imageUrl
) {

    public static MealKitImageDto of(Long id, String imageUrl) {
        return new MealKitImageDto(id, imageUrl);
    }

    public static MealKitImageDto from(MealKitImage entity) {
        return new MealKitImageDto(
                entity.getId(),
                entity.getImageUrl()
        );
    }
}
