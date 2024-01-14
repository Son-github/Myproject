package com.son.project_a.dto.response;

import com.son.project_a.dto.MealKitSiteDto;

import java.io.Serializable;

public record MealKitSiteResponse(
        Long id,
        String siteName,
        int sitePrice,
        String siteUrl
) implements Serializable {

    public static MealKitSiteResponse of(Long id, String siteName, int sitePrice, String siteUrl) {
        return new MealKitSiteResponse(id, siteName, sitePrice, siteUrl);
    }

    public static MealKitSiteResponse from(MealKitSiteDto dto) {
        return new MealKitSiteResponse(
                dto.id(),
                dto.siteName(),
                dto.sitePrice(),
                dto.siteUrl()
        );
    }
}
