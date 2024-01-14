package com.son.project_a.dto;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitSite;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitDto(
        Long id,
        String mName,
        int mPrice,
        String mCategory,
        String mContent,
        int mSaleUnit,
        String mSaleCompany,
        int mWeight,
        Set<MealKitSiteDto> mealKitSiteDtos,
        Set<MealKitImageDto> mealKitImageDtos,
        LocalDateTime createAt,
        LocalDateTime modifiedAt
) {

    public static MealKitDto of(
            String mName, int mPrice, String mCategory, String mContent,
            int mSaleUnit,  String mSaleCompany, int mWeight, Set<MealKitSiteDto> mealKitSiteDtos,
            Set<MealKitImageDto> mealKitImageDtos
    ){
        return new MealKitDto(
                null, mName, mPrice, mCategory,
                mContent, mSaleUnit, mSaleCompany,
                mWeight, mealKitSiteDtos, mealKitImageDtos, null, null);
    }

    public static MealKitDto from(MealKit entity) {
        return new MealKitDto(
                entity.getId(),
                entity.getMName(),
                entity.getMPrice(),
                entity.getMCategory(),
                entity.getMContent(),
                entity.getMSaleUnit(),
                entity.getMSaleCompany(),
                entity.getMWeight(),
                entity.getMealKitSites().stream()
                        .map(MealKitSiteDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getMealKitImages().stream()
                                .map(MealKitImageDto::from)
                                .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public MealKit toEntity() {
        return MealKit.of(
                mName,
                mPrice,
                mCategory,
                mContent,
                mSaleUnit,
                mSaleCompany,
                mWeight
        );
    }
}
