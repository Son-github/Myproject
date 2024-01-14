package com.son.project_a.domain;


import com.son.project_a.domain.constant.Authority;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 한번에 설정해줌.
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserAccount extends AuditingFields {
    @Id
    @Column(length = 100)
    private String userEmail;

    @Column private String userPassword;
    @Column private String firstName;
    @Column private String lastName;
    @Column private String nickName;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    protected UserAccount() {}

    public UserAccount(String userEmail, String userPassword, String firstName, String lastName, String nickName, Authority authority) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.authority = authority;
    }

    public static UserAccount of(String userEmail, String userPassword) {
        return UserAccount.of(userEmail, userPassword, null, null, null);
    }
    public static UserAccount of(String userEmail, String userPassword, String nickName) {
        return UserAccount.of(userEmail, userPassword,null, null, nickName);
    }

    public static UserAccount of(String userEmail, String userPassword, String firstName, String lastName, String nickName) {
        return new UserAccount(userEmail, userPassword, firstName, lastName, nickName, Authority.ROLE_USER);
    } //TODO: 인증을 Authority.ROLE_USER이 Default되도록 해놓음.. 공부하고 바꾸기

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userEmail);
    }
}
