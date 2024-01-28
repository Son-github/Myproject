package com.son.project_a.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountResponse {
    private String userEmail;
    private String token;

/*    public static UserAccountResponseDto of(UserAccount userAccount) { // Response를 보낼 때 쓰이는 dto
        return UserAccountResponseDto.builder()
                .userEmail(userAccount.getUserEmail())
                .nickname(userAccount.getNickName())
                .firstname(userAccount.getFirstName())
                .lastname(userAccount.getLastName())
                .build();
    }*/
}
/*public record UserAccountResponseDto(
        String userEmail,
        String nickname
) {

    public static UserAccountResponseDto of(UserAccount userAccount) { // Response를 보낼 때 쓰이는 dto
        return UserAccountResponseDto.builder()
                .userEmail(userAccount.getUserEmail())
                .nickname(userAccount.getNickName())
                .build();
    }
}*/
