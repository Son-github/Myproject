package com.son.project_a.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data
@ToString(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MealKitSite extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JsonIgnore private MealKit mealKit;

    @Column private String siteName;

    @Column private int sitePrice;

    @Column(length = 20000) private String siteUrl;

    protected MealKitSite() {}

    private MealKitSite(MealKit mealKit, String siteUrl) {
        this.mealKit = mealKit;
        this.siteUrl = siteUrl;
    }

    public static MealKitSite of(MealKit mealKit, String siteUrl) {
        return new MealKitSite(mealKit, siteUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealKitSite that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
