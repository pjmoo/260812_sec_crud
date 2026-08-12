package org.example.sec_crud.controller;

import org.example.sec_crud.domain.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String index(
            // 세션에서 인증된 사용자 정보를 가져옴
            // -> UserDetails를 상속한 타입을 가져올 수 있음 (다운캐스팅)
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model
    ) {
        model.addAttribute("user", userDetails);
        return "index";
    }
}
