package com.son.project_a.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.son.project_a.domain.MealKitSite;
import com.son.project_a.domain.QMealKitImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MealKitSiteRepository extends
        JpaRepository<MealKitSite, Long>,
        QuerydslPredicateExecutor<MealKitSite>,
        QuerydslBinderCustomizer<QMealKitImage> {

    @Override
    default void customize(QuerydslBindings bindings, QMealKitImage root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.createdAt);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    };
}
