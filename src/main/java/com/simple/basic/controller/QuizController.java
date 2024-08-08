package com.simple.basic.controller;

import com.simple.basic.command.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class QuizController {

    @GetMapping("/quiz01")
    public String quiz01(Model model) {
        TestVO vo = TestVO.builder().num(1).name("아무개").age(54).build();
        model.addAttribute("vo", vo);
        return "view/quiz01";
    }

    @GetMapping("/quiz01_result")
    public String quiz_result(@ModelAttribute("num") String num) { // 다음 화면에서 num 으로 값을 넘겨줌
        return "view/quiz01_result";
    }
}
