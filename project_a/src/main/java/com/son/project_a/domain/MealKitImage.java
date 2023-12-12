package com.son.project_a.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data
@ToString(callSuper = false) //ToString을 이용하면 클래스명(필드명1 = 필드값1, 필드명2 = 필드값2...) 식으로 출력도미
// @EqualsAndHashCode(callSuper = true)을 사용하면 부모 클래스 필드 값들도 동일한지 체크하며, callSuper = false로 설정하면 자신 클래스의 필드 값들만 고려함.
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MealKitImage extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private MealKit mealKit;

    @Column(length = 10000)
    private String imageUrl;

    protected MealKitImage() {}

    private MealKitImage(MealKit mealKit, String imageUrl) {
        this.mealKit = mealKit;
        this.imageUrl = imageUrl;
    }

    public static MealKitImage of(MealKit mealKit, String imageUrl) {
        return new MealKitImage(mealKit, imageUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealKitImage that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
