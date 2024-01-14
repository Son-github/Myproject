package com.son.project_a.controller;

import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.dto.response.UserAccountResponseDto;
import com.son.project_a.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class SecurityController {

    Logger log = LoggerFactory.getLogger(SecurityController.class);

    private final UserAccountService userAccountService;

    @GetMapping("/me")
    public ResponseEntity<UserAccountResponseDto> getMyInfo() {
        UserAccountResponseDto myInfoBySecurity = userAccountService.getMyInfoWithSecurity();

        log.info("myInfoWithSecurity의 nickname: {}", myInfoBySecurity.getNickname());
        return ResponseEntity.ok((myInfoBySecurity));

    }
}
