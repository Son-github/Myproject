package com.son.project_a.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MealKitComment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000) private String title;
    @ManyToOne(optional = false) private MealKit mealKit; // 밀키트(ID)
    @ManyToOne(optional = false) private UserAccount userAccount; // 작성자(ID)
    @Column(length = 60000) private String content;


    protected MealKitComment() {}

    private MealKitComment(MealKit mealKit, UserAccount userAccount, String content) {
        this.userAccount = userAccount;
        this.mealKit = mealKit;
        this.content = content;
    }

    public static MealKitComment of(MealKit mealKit, UserAccount userAccount, String content) {
        return new MealKitComment(mealKit, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealKitComment that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
