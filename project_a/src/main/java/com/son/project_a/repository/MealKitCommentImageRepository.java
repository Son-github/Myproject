package com.son.project_a.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.son.project_a.domain.MealKitCommentImage;
import com.son.project_a.domain.QMealKitCommentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MealKitCommentImageRepository extends
        JpaRepository<MealKitCommentImage, Long>,
        QuerydslPredicateExecutor<MealKitCommentImage>,
        QuerydslBinderCustomizer<QMealKitCommentImage> {

    // todo: customize 한번 더 살펴보기!!

    @Override
    default void customize(QuerydslBindings bindings, QMealKitCommentImage root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.createdAt);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    };
}
