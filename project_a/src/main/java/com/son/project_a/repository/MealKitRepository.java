package com.son.project_a.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.QMealKit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MealKitRepository extends
        JpaRepository<MealKit, Long>,
        QuerydslPredicateExecutor<MealKit>,
        QuerydslBinderCustomizer<QMealKit> {


    Page<MealKit> findBymName(String mName, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QMealKit root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.mName, root.mCategory, root.mContent, root.mPrice);
        bindings.bind(root.mName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.mCategory).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.mContent).first(StringExpression::containsIgnoreCase);
    };
}
