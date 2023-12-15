package com.son.project_a.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitImage;
import com.son.project_a.domain.QMealKitImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface MealKitImageRepository extends
        JpaRepository<MealKitImage, Long>,
        QuerydslPredicateExecutor<MealKitImage>,
        QuerydslBinderCustomizer<QMealKitImage> {

    List<MealKitImage> findMealKitImageByMealKitId(Long id);

    @Override
    default void customize(QuerydslBindings bindings, QMealKitImage root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.createdAt);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }
}
