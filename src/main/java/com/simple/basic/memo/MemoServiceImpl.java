package com.simple.basic.memo;

import com.simple.basic.command.MemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("memoService")
public class MemoServiceImpl implements MemoService {
    @Autowired
    MemoMapper memoMapper; // 맵퍼 구현체 넣기

    @Override
    public void insert(MemoVO vo) {
        memoMapper.insert(vo);
    }

    @Override
    public ArrayList<MemoVO> getList() {
        return memoMapper.getList();
    }
}
