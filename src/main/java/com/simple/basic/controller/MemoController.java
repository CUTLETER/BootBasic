package com.simple.basic.controller;

import com.simple.basic.memo.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memo")
public class MemoController {
    // insert 기능 - 유효성 검증
    // select 기능 - 화면 처리
    
    
    @Autowired
    @Qualifier("memoService") // @Service("memoService") 동일하게
    MemoService memoService;

    @GetMapping("/memoWrite")
    public String memoWrite() {
        return "memo/memoWrite";
    }
}
