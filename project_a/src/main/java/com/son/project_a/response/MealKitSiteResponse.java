package com.son.project_a.response;

import com.son.project_a.dto.MealKitSiteDto;

import java.io.Serializable;

public record MealKitSiteResponse(
        Long id,
        String siteUrl
) implements Serializable {

    public static MealKitSiteResponse of(Long id, String siteUrl) {
        return new MealKitSiteResponse(id, siteUrl);
    }

    public static MealKitSiteResponse from(MealKitSiteDto dto) {
        return new MealKitSiteResponse(
                dto.id(),
                dto.siteUrl()
        );
    }
}
