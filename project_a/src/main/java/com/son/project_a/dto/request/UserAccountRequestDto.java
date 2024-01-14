package com.son.project_a.dto.request;

import com.son.project_a.domain.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountRequestDto {
    private String userEmail;
    private String userPassword;
    private String nickname;
    private String firstname;
    private String lastname;

    public UserAccount toUserAccount(PasswordEncoder passwordEncoder) {
        return UserAccount.of(
                userEmail,
                passwordEncoder.encode(userPassword),
                firstname,
                lastname,
                nickname
        );
    }

    public UsernamePasswordAuthenticationToken toAuthentication() { // UsernamePasswordAuthenticationToken toAuthentication를 반환하여 userEamil과 userPassword가 일치하는지 검증
        return new UsernamePasswordAuthenticationToken(userEmail, userPassword);
    }
}

/*public record UserAccountRequestDto(
        String userEmail,
        String userPassword,
        String firstName,
        String lastName,
        String nickname
) {

    public UserAccount toUserAccount(PasswordEncoder passwordEncoder) {
        return UserAccount.of(
                userEmail,
                passwordEncoder.encode(userPassword),
                firstName,
                lastName,
                nickname
        );
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userEmail, userPassword);
    }
}*/
