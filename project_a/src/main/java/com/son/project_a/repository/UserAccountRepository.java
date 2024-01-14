package com.son.project_a.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.son.project_a.domain.QUserAccount;
import com.son.project_a.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserAccountRepository extends
        JpaRepository<UserAccount, String>,
        QuerydslPredicateExecutor<UserAccount>,
        QuerydslBinderCustomizer<QUserAccount> { // JpaRepository<도메인 객체, Id>를 상속
        boolean existsByUserEmail(String userEmail);
        boolean existsByUserPassword(String userPassword);

        Optional<UserAccount> findByUserEmail(String email);

        @Override
        default void customize(QuerydslBindings bindings, QUserAccount root) {
                bindings.excludeUnlistedProperties(true); // 우리가 원하는 필드만 검색할 수 있게 설정하기 위해서 사용. true로 하면 list하지 않은 property는 검색에서 제외
                bindings.including(root.userEmail, root.userPassword, root.createdAt, root.firstName, root.lastName); // 검색을 원하는 필드

                //bindings.bind(root.userEmail).first(StringExpression::likeIgnoreCase); // 부분 검색을 진행할 때에 검색어에 %를 넣어주어야 하는데 likeIgnoreCase를 사용하면 %를 직접 넣어주어야 함.
                bindings.bind(root.userEmail).first(StringExpression::containsIgnoreCase); // containsIgnoreCase는 자동으로 %를 넣어줌
                bindings.bind(root.firstName).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.lastName).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        };
}
