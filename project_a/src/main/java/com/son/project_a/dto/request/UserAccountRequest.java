package com.son.project_a.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountRequest {
    private String userEmail;
    private String userPassword;
    private String nickname;
    private String firstname;
    private String lastname;
}
