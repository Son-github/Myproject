package com.son.project_a.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String userEmail;
    @Column private String userPassword;
    @Column private String firstName;
    @Column private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    protected UserAccount() {}

    private UserAccount(String userEmail, String userPassword, String firstName, String lastName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserAccount of(String userEmail, String userPassword, String firstName, String lastName) {
        return new UserAccount(userEmail, userPassword, firstName, lastName);
    }
}
