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
public class MealKit extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mName;
    @Column
    private String mPrice;
    @Column
    private String mCategory;
    @Column
    private int mStock;
    @Column(length = 65535)
    private String mContent;

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

    public MealKit(String mName, String mPrice, String mCategory, int mStock, String mContent) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.mStock = mStock;
        this.mContent = mContent;
    }

    public static MealKit of(String mName, String mPrice, String mCategory, int mStock, String mContent) {
        return new MealKit(mName, mPrice, mCategory, mStock, mContent);
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
