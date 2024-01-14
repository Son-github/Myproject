package com.son.project_a.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MealKit extends AuditingFields { //TODO: 칼럼 구체화
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mName;
    @Column
    private int mPrice;
    @Column
    private String mCategory;
    @Column(length = 65535)
    private String mContent;
    @Column
    private int mSaleUnit; // 판매 단위
    @Column
    private String mSaleCompany; // 판매사
    @Column
    private int mWeight; // 판매중량

    @ToString.Exclude
    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL)
    private final Set<MealKitSite> mealKitSites = new LinkedHashSet<>();


    @ToString.Exclude
    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL)
    private final Set<MealKitImage> mealKitImages = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL)
    private final Set<MealKitComment> mealKitComments = new LinkedHashSet<>();

    protected MealKit() {
    }

    public MealKit(String mName, int mPrice, String mCategory, String mContent, int mSaleUnit, String mSaleCompany, int mWeight) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.mContent = mContent;
        this.mSaleUnit = mSaleUnit;
        this.mSaleCompany = mSaleCompany;
        this.mWeight = mWeight;
    }

    public static MealKit of(String mName, int mPrice, String mCategory,String mContent, int mSaleUnit, String mSaleCompany, int mWeight) {
        return new MealKit(mName, mPrice, mCategory, mContent, mSaleUnit, mSaleCompany,mWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealKit mealKit)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, mealKit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
