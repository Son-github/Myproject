package com.son.project_a.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data
@ToString(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MealKitCommentImage extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private MealKitComment mealKitComment;

    @Column(length = 10000)
    private String commentImageUrl;

    protected MealKitCommentImage() {}

    private MealKitCommentImage(MealKitComment mealKitComment, String commentImageUrl) {
        this.mealKitComment = mealKitComment;
        this.commentImageUrl = commentImageUrl;
    }

    public static MealKitCommentImage of(MealKitComment mealKitComment, String commentImageUrl) {
        return new MealKitCommentImage(mealKitComment, commentImageUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealKitCommentImage that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
