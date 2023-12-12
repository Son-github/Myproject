package com.son.project_a.response;

import com.son.project_a.dto.MealKitImageDto;

import java.io.Serializable;

public record MealKitImageResponse(
        Long id,
        String imageUrl
) implements Serializable {

    public static MealKitImageResponse of(Long id, String imageUrl) {
        return new MealKitImageResponse(id, imageUrl);
    }

    public static MealKitImageResponse from(MealKitImageDto dto) {
        return new MealKitImageResponse(
                dto.id(),
                dto.imageUrl()
        );
    }
}
