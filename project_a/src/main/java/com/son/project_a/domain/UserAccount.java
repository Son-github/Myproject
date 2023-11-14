package com.son.project_a.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 한번에 설정해줌.
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAccountId;

    @Column private String userEmail;
    @Column private String userPassword;
    @Column private String firstName;
    @Column private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    protected UserAccount() {}

    public UserAccount(String userEmail, String userPassword, String firstName, String lastName) {
        this.createdAt = LocalDateTime.now();
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserAccount of(String userEmail, String userPassword, String firstName, String lastName) {
        return new UserAccount(userEmail, userPassword, firstName, lastName);
    }
}
