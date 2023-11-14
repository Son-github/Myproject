package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;

import java.time.LocalDateTime;

public record MealKitDto(

        Long mealKitId,
        String mName,
        Long mPrice,
        String mCategory,
        Long mStock,
        String mSite,
        String mContent,
        String mImage,
        LocalDateTime createAt
) {

    public static MealKitDto of(
            String mName, Long mPrice, String mCategory,
            Long mStock, String mSite, String mContent, String mImage
    ){
        return new MealKitDto(
                null, mName, mPrice, mCategory, mStock,
                mSite, mContent, mImage, null);
    }

    public static MealKitDto from(MealKit entity) {
        return new MealKitDto(
                entity.getMealKitId(),
                entity.getMName(),
                entity.getMPrice(),
                entity.getMCategory(),
                entity.getMStock(),
                entity.getMSite(),
                entity.getMContent(),
                entity.getMImage(),
                entity.getCreatedAt()
        );
    }

    public MealKit toEntity() {
        return MealKit.of(
                mName,
                mPrice,
                mCategory,
                mStock,
                mSite,
                mContent,
                mImage
        );
    }
}
