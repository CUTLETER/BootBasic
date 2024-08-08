package com.simple.basic;

import com.simple.basic.command.BuilderVO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트 코드의 순서를 지정함
public class TestCode01 {

    // alt + enter 테스트 실행 아이콘 나타내기
    // 테스트 코드가 매번 위에서부터 아래로 완료되는 건 아님
//    @Test
//    @Order(2) // 두 번째
//    public void testCode01() {
//        System.out.println("테스트 코드1 실행됨");
//    }
//
//    @Test
//    @Order(1) // 첫 번째
//    public  void testCode02() {
//        System.out.println("테스트 코드2 실행됨");
//    }
    @Test
    public void testCode03() {

        // 한번 값이 지정되면, 변수 값을 바꿀 수 없는 불변성 제공
        // 값의 타입을 명확하게 확인할 수 있음
        // getter, setter 쓰지 말라는 것은 아님 XXXx
        BuilderVO vo = BuilderVO.builder().name("홍길동").age(10).build();
        System.out.println(vo.toString());
    }

}
