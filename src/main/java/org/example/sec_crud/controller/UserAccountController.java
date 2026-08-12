package org.example.sec_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }
}
