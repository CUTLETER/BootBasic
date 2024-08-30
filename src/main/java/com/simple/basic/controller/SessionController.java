package com.simple.basic.controller;

import com.simple.basic.command.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class SessionController {

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "/user/mypage";
    }

    @GetMapping("/success")
    public String success(HttpSession session) {
        UserVO vo = (UserVO) session.getAttribute("userVO");
        if(vo == null) {
            return "redirect:/user/login";
        }
        return "/user/success";
    }


    // 로그인 요청
    @PostMapping("/loginForm")
    public String loginForm(UserVO vo, HttpSession session) {
        // 로그인 시도
        // SELECT * FROM 테이블명 WHERE ID = ? AND PW = ?
        // 서비스, 맵퍼 등 생략 (원래는 result 결과를 저 너머에서 받아와야 하지만 지금은 대충 대충)
        // UserVO result = null; // 로그인 실패라 가정하면
        UserVO result = vo; // 로그인 성공이라 가정하면
        if(result == null) {
            // 로그인 실패
            return "redirect:/user/login";
        } else {
            // 로그인 성공 (세션에 회원 정보 저장함)
            session.setAttribute("userVO", result);
            return "redirect:/user/success";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션의 무효화
        return "redirect:/user/login";
    }

}
