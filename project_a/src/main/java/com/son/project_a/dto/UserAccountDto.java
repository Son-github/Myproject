package com.son.project_a.dto;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.domain.constant.Authority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public record UserAccountDto(
        /* 테이블에 있는 데이터에서 필요한 데이터만 가져오는 역할을 함.
        * 예를 들어, 상품소개 API, Q&A API, 유저 API 이렇게 3개의 API를 호출할 때,
        * 3번의 통신을 하지 않고 3개의 데이터를 담고있는 dto를 만들어 API 1번의 호출로 바꾸는 것.
        *
        * */
        String userEmail,
        String userPassword,
        String firstName,
        String lastName,
        String nickName,
        LocalDateTime createAt
) {

    public static UserAccountDto of(String userEmail, String userPassword, String firstName, String lastName, String nickName) {
        return new UserAccountDto( userEmail, userPassword, firstName, lastName, nickName,null);
    }

    public static UserAccountDto of( String userEmail, String userPassword, String firstName, String lastName, String nickName, LocalDateTime createAt) {
        return new UserAccountDto(userEmail, userPassword, firstName, lastName, nickName, createAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserEmail(),
                entity.getUserPassword(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNickName(),
                entity.getCreatedAt()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userEmail,
                userPassword,
                firstName,
                lastName,
                nickName
        );
    } //TODO: 인증을 Authority.ROLE_USER이 Default되도록 해놓음.. 공부하고 바꾸기
}
