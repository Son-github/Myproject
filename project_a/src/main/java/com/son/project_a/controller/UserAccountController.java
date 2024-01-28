package com.son.project_a.controller;

import com.son.project_a.dto.request.UserAccountRequest;
import com.son.project_a.dto.response.UserAccountResponse;
import com.son.project_a.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 이 어노테이션이 없다면 하나하나 @Autowired를 선언해줘야하지만 이 것이 있음으로써 안해도됨.
@RestController
public class UserAccountController {

    private static Logger log = LoggerFactory.getLogger(UserAccountController.class); // log를 띄우기 위해 사용


    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserAccountResponse> signup(@RequestBody UserAccountRequest requestDto) {
        log.info("requestDto: {}", requestDto);
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @RequestMapping("/signin")
    public ResponseEntity<UserAccountResponse> signin(@RequestBody UserAccountRequest requestDto) {
        return ResponseEntity.ok(authService.signIn(requestDto));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
