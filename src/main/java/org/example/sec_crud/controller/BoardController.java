package org.example.sec_crud.controller;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.BoardFormDTO;
import org.example.sec_crud.domain.entity.BoardEntity;
import org.example.sec_crud.domain.entity.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("board", new BoardFormDTO("", ""));
        model.addAttribute("list", boardService.findAll());
        return "board/page";
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "id") Long userId,
            Model model) {
        // 어차피 수정을 해줄 거면
        // 작성자 = 수정자
        BoardEntity b = boardService.findById(id);
        if (!b.getWriter().getId().equals(userId)) {
            return "redirect:/board";
        }
        model.addAttribute("board", b);
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

    @PostMapping("/{id}")
    public String update(
            @AuthenticationPrincipal(expression = "id") Long userId,
            @PathVariable Long id,
            @ModelAttribute("board") BoardFormDTO boardFormDTO
    ) {
        boardService.update(userId, id, boardFormDTO);
        return "redirect:/board";
    }
}
