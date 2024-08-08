package com.simple.basic.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memoService")
public class MemoServiceImpl implements MemoService {
    @Autowired
    MemoMapper memoMapper; // 맵퍼 구현체 넣기
}
