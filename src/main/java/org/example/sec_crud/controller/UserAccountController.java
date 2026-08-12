package org.example.sec_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.UserJoinFormDTO;
import org.example.sec_crud.domain.dto.UserLoginFormDTO;
import org.example.sec_crud.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new UserLoginFormDTO("", ""));
        return "user/login";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("form", new UserJoinFormDTO("", ""));
        return "user/join";
    }

    @PostMapping("/join")
    public String join(
            @Validated @ModelAttribute("form") UserJoinFormDTO form,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/join";
        }
        userAccountService.join(form);
        return "redirect:/user/login";
    }
}
