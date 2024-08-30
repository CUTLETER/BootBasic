package com.simple.basic.interceptor;

import com.simple.basic.command.UserVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuthHandler implements HandlerInterceptor {
    // 1. 인터셉터로 동작을 하려면 먼저 HandlerInterceptor 상속 받아야 함
    // 2. Ctrl + i 로 두 가지 오버라이딩 해야 함
    // 3. 인터셉터를 설정 파일에 bean으로 등록한 다음 인터셉터 클래스로 다시 등록해야 함

    // 컨트롤러 이전에 동작함
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("컨트롤러 작동 이전에 동작함!");

        // 세션 검사 -> 세션이 있으면 로그인 된 유저이기 때문에 컨트롤러와 연결시키고
        //            세션이 없으면 로그인 못한 유저이기 때문에 로그인 페이지로 이동시키기

        HttpSession session = request.getSession(); // 현재 세션
        UserVO vo = (UserVO)session.getAttribute("userVO"); // 로그인 성공 시 저장해둔 세션 값

        if (vo == null) { // 로그인 O
           response.sendRedirect("/user/login");
           return false; // 컨트롤러 동작 XXXXXX
        } else { // 로그인 X
            return true; // 컨트롤러로 연결 시키기
        }
        // return true; // true일 시 컨트롤러를 동작시키며 false는 컨트롤러를 동작시키지 않음
    }

    // 컨트롤러 이후에 동작함
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("컨트롤러 작동 이후에 동작함!");
    }
}
