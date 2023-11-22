package com.son.project_a.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 한번에 설정해줌.
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserAccount extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String userEmail;
    @Column private String userPassword;
    @Column private String firstName;
    @Column private String lastName;

    protected UserAccount() {}

    public UserAccount(String userEmail, String userPassword, String firstName, String lastName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserAccount of(String userEmail, String userPassword, String firstName, String lastName) {
        return new UserAccount(userEmail, userPassword, firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
