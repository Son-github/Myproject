package com.son.project_a.repository;

import com.son.project_a.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> { // JpaRepository<도메인 객체, Id>를 상속
        boolean existsByUserEmail(String userEmail);
        boolean existsByUserPassword(String userPassword);
}
