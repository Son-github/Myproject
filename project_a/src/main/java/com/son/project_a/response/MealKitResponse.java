package com.son.project_a.response;

import com.son.project_a.dto.MealKitDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MealKitResponse(
        Long id,
        String mName,
        String mPrice,
        String mCategory,
        int mStock,
        String mSite,
        String mContent,
        String mImage,
        LocalDateTime createdAt
) implements Serializable {

    public static MealKitResponse of(Long id, String mName, String mPrice, String mCategory,
                                     int mStock, String mSite, String mContent, String mImage, LocalDateTime createdAt) {
        return new MealKitResponse(id, mName, mPrice, mCategory, mStock, mSite, mContent, mImage, createdAt);
    }

    public static MealKitResponse from(MealKitDto dto) {
        return new MealKitResponse(
                dto.id(),
                dto.mName(),
                dto.mPrice(),
                dto.mCategory(),
                dto.mStock(),
                dto.mSite(),
                dto.mContent(),
                dto.mImage(),
                dto.createAt()
        );
    }
}
