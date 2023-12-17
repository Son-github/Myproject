package com.son.project_a.dto;

import com.son.project_a.domain.MealKitSite;

public record MealKitSiteDto(
        Long id,
        String siteUrl
) {

    public static MealKitSiteDto of(Long id, String siteUrl) {
        return new MealKitSiteDto(id, siteUrl);
    }

    public static MealKitSiteDto from(MealKitSite entity) {
        return new MealKitSiteDto(
                entity.getId(),
                entity.getSiteUrl()
        );
    }
}
