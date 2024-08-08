package com.simple.basic.controller;

import com.simple.basic.command.ValidVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/valid")
public class ValidController {

    @GetMapping("/view")
    public String view(Model model) {
        // 이 화면에 처음 진입할 때 유효성 검증 때문에 vo에 값이 비어 있으면 에러 남
        // vo.name = null.name 이런 상태이기 때문에
        // vo 객체 하나 화면에 진입할 때 처음부터 넣어주면 해결됨 (쉬운 방식)
        // 아니면 클라이언트 측 서버에서 th:if로 조건문 처리하면 됨
        model.addAttribute("vo", new ValidVO());
        return "valid/view";
    }

    @PostMapping("/actionForm")
    // @ModelAttribute("vo") 추가하면 유효성 검증 통과 실패 시 사용자가 입력한 값 유지시키고 화면으로 다시 나갈 수 있음
    // 없으면 유효성 검증 실패 시 입력 값이 전부 다 날아가서 다시 입력해야 함
    public String actionForm(@Valid @ModelAttribute("vo") ValidVO vo, BindingResult binding) {
        // @Valid 는 유효성 검사를 하겠다는 뜻
        // 만약 유효성 검사에 통과하지 못하면, 통과하지 못한 멤버변수 내역이 BindingResult에 저장됨

        if(binding.hasErrors()) { // 내역이 있으면 true, 없으면 false
//            System.out.println("유효성 검증 실패!");
//
//            List<FieldError> list = binding.getFieldErrors(); // 유효성 검사에 실패한 목록 확인 가능
//
//            for (FieldError error : list) {
//                System.out.println(error.getField()); // 유효성 검사에 실패한 필드명 확인 가능
//                System.out.println(error.getDefaultMessage()); // 유효성 검사에 실패한 내역의 메세지 값 확인 가능
//            }
            return "valid/view"; // 다시 뷰 화면으로
        }

        System.out.println(vo.toString());
        return "valid/result";
    }
}
