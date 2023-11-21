package com.son.project_a.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.QMealKitComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MealKitCommentRepository extends
        JpaRepository<MealKitComment, Long>,
        QuerydslPredicateExecutor<MealKitComment>,
        QuerydslBinderCustomizer<QMealKitComment> {

    List<MealKitComment> findByMealKitId(Long mealKitId);

    @Override
    default void customize(QuerydslBindings bindings, QMealKitComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.createdAt);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }


}
