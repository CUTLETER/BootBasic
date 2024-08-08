package com.simple.basic.memo;

import org.apache.ibatis.annotations.Mapper;

@Mapper // Mapper 어노테이션이 붙은 인터페이스를 Mybatis가 인식하기 때문에 꼭 넣어줘야 함!!
public interface MemoMapper {

    public String hello();
}
