package com.son.project_a.dto.security;

import com.son.project_a.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record MealKitBoardPrincipal(
        String userName,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String firstName,
        String lastName,
        String nickname
) implements UserDetails {

    public static MealKitBoardPrincipal of(String userName, String password, String firstName, String lastName, String nickname) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new MealKitBoardPrincipal(
                userName,
                password,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                firstName,
                lastName,
                nickname
        );
    }

    public static MealKitBoardPrincipal from(UserAccountDto dto) {
        return MealKitBoardPrincipal.of(
                dto.userEmail(),
                dto.userPassword(),
                dto.firstName(),
                dto.lastName(),
                dto.nickName()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                userName,
                password,
                firstName,
                lastName,
                nickname
        );
    }

    @Override public String getUsername() { return userName; }

    @Override public String getPassword() { return password; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }

    @Override public boolean isAccountNonLocked() { return true; }

    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter private final String name;

        RoleType(String name) { this.name = name; }
    }
}
