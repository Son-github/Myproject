package com.son.project_a.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    static Logger log = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {}

    public static String getCurrentMemberEmail() { // Request가 들어오면 jwtFilter의 DoFilter에서 저장되는데 거기에 있는 인증정보를 꺼내서, 그 안의 id를 반환

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("authentication: {}", authentication);

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }


        return authentication.getName();
    }
}
