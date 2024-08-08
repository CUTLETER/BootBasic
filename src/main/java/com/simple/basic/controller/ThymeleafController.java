package com.simple.basic.controller;

import com.simple.basic.command.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/view") // 폴더 구조에 맞추기
public class ThymeleafController {

    @GetMapping("/ex01")
    public String ex01() {
        return "view/ex01";
    }

    @GetMapping("/ex02")
    public String ex02(Model model) {
        ArrayList<TestVO> list = new ArrayList<>();
        for (int i=0; i<=10; i++) {
           TestVO vo = TestVO.builder()
                            .num(i)
                            .name("홍"+i)
                            .addr("서울시"+i)
                            .age(20+i)
                            .build();
        list.add(vo);
        }
        model.addAttribute("list", list); // list를 모델에 저장시킴
        return  "view/ex02";
    }

    @GetMapping("/ex03")
    public String ex03(Model model) {
        ArrayList<TestVO> list = new ArrayList<>();
        for (int i=0; i<=10; i++) {
            TestVO vo = TestVO.builder()
                    .num(i)
                    .name("홍"+i)
                    .addr("서울시"+i)
                    .age(20+i)
                    .build();
            list.add(vo);
        }
        model.addAttribute("list", list); // list를 모델에 저장시킴
        return  "view/ex03";
    }

    @GetMapping("/ex03_result")
    public String result() {
        return "view/ex03_result";
    }

    // 가변 매개변수는 {}안에 담음
    @GetMapping("/ex03_result2/{name}/{age}")
    // {name} -> "name" -> String name 순으로 값이 넘어와서 저장됨
    public String result2(@PathVariable("name") String name,
                          @PathVariable("age") int age) {
        System.out.println(name+""+age);
        return "view/ex03_result";
    }

    @GetMapping("/ex04")
    public String ex04(Model model) {
        TestVO vo = TestVO.builder()
                .num(1)
                .name("홍")
                .addr("서울시")
                .age(20)
                .build();

        model.addAttribute("name", "홍길동");
        model.addAttribute("arr", new Integer[] {1,2,3});
        model.addAttribute("vo", vo);

        model.addAttribute("list", Arrays.asList(1,2,3,4));
        model.addAttribute("now", new Date());
        return "view/ex04";
    }

    @GetMapping("/ex05")
    public String ex05() {
        return "view/ex05";
    }

    @GetMapping("/ex06")
    public String ex06() {
        return "view/ex06";
    }
}
