package com.son.project_a.dto;

import com.son.project_a.domain.MealKitSite;

public record MealKitSiteDto(
        Long id,
        String siteName,
        int sitePrice,
        String siteUrl

) {

    public static MealKitSiteDto of(Long id, String siteName, int sitePrice, String siteUrl) {
        return new MealKitSiteDto(id, siteName, sitePrice, siteUrl);
    }

    public static MealKitSiteDto from(MealKitSite entity) {
        return new MealKitSiteDto(
                entity.getId(),
                entity.getSiteName(),
                entity.getSitePrice(),
                entity.getSiteUrl()
        );
    }
}
