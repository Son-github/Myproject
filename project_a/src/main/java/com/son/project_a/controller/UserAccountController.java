package com.son.project_a.controller;

import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.UserAccountDto;
import com.son.project_a.dto.jwt.TokenDto;
import com.son.project_a.dto.request.UserAccountRequestDto;
import com.son.project_a.dto.response.UserAccountResponseDto;
import com.son.project_a.repository.UserAccountRepository;
import com.son.project_a.service.AuthService;
import com.son.project_a.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 이 어노테이션이 없다면 하나하나 @Autowired를 선언해줘야하지만 이 것이 있음으로써 안해도됨.
@RestController
public class UserAccountController {

    private static Logger log = LoggerFactory.getLogger(UserAccountController.class); // log를 띄우기 위해 사용


    private final AuthService authService;
    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public ResponseEntity<UserAccountResponseDto> signup(@RequestBody UserAccountRequestDto requestDto) {
        log.info("requestDto: {}", requestDto);
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @RequestMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody UserAccountRequestDto requestDto) {
        return ResponseEntity.ok(authService.signin(requestDto));
    }

    /*@ResponseBody // @RequestBody, @ResponseBody가 왜 필요한가요?
    @PostMapping("/signup")
    public void saveUserAccount(@RequestBody UserAccount userAccount) {
        if (userAccountRepository.existsByUserEmail(userAccount.getUserEmail())){
            log.info("이미 있엉!");
        } else {
            userAccountService.saveUserAccount(userAccount);
            log.info("잘 들어왔니? {}", userAccount.getCreatedAt());
        }
    }*/

/*    @ResponseBody
    @PostMapping("/signin")
    public void login(@RequestBody UserAccountDto dto) {
        if (userAccountService.userCorrect(dto)) {
            log.info("있군요!");
        } else {
            log.info("없군요ㅠㅠ");
        }

    }*/
}
