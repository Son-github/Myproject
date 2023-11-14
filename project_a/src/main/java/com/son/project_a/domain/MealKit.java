package com.son.project_a.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MealKit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealKitId;

    @Column private String mName;
    @Column private String mPrice;
    @Column private String mCategory;
    @Column private Long mStock;
    @Column private String mSite;
    @Column private String mContent;
    @Column private String mImage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    protected MealKit() {}

    public MealKit(String mName, String mPrice, String mCategory, Long mStock, String mSite, String mContent, String mImage) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.mStock = mStock;
        this.mSite = mSite;
        this.mContent = mContent;
        this.mImage = mImage;
    }

    public static MealKit of(String mName, String mPrice, String mCategory, Long mStock, String mSite, String mContent, String mImage) {
        return new MealKit(mName, mPrice, mCategory, mStock, mSite, mContent, mImage);
    }

}
