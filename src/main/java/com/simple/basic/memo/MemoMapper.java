package com.simple.basic.memo;

import com.simple.basic.command.MemoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper // Mapper 어노테이션이 붙은 인터페이스를 Mybatis가 인식하기 때문에 꼭 넣어줘야 함!!
public interface MemoMapper {
    public String hello();
    public void insert(MemoVO vo);
    public ArrayList<MemoVO> getList();
}
