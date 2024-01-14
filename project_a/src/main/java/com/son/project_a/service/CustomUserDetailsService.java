/*package com.son.project_a.service;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUserEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 을 DB에서 첮을 수 없습니다."));
    }

    private UserDetails createUserDetails(UserAccount userAccount) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userAccount.getAuthority().toString());

        return new User(
                userAccount.getUserEmail(),
                userAccount.getUserPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}*/
