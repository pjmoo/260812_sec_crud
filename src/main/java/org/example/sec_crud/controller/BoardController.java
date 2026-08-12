package org.example.sec_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.BoardFormDTO;
import org.example.sec_crud.domain.entity.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("board", new BoardFormDTO("", ""));
        return "board/page";
    }

    @PostMapping
    public String write(
            @AuthenticationPrincipal(expression = "id") Long id,
            @ModelAttribute("board") BoardFormDTO boardFormDTO
    ) {
        boardService.write(id, boardFormDTO);
        return "redirect:/board";
    }
}
