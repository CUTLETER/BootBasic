package com.simple.basic.command;

import lombok.Builder;
import lombok.ToString;

@Builder // Builder 패턴 간단하게 쓰는 법 2
@ToString
public class BuilderVO {

//      내부 클래스
// - 클래스 안의 클래스
// - 내부 클래스 종류 inner 클래스, static inner 클래스
//
//      inner 클래스
// - 안에서 밖으로 접근이 가능함(외부 참조 가능함)
// - 밖에서 안으로도 접근 가능함
//
//      static inner 클래스
// - 안에서 밖으로 접근이 불가능함(외부 참조 불가능함)
// - 밖에서 안으로도 접근 가능함


    // 1. 멤버변수
    private String name;
    private int age;

    /* 생성 패턴 중 하나인 Builder 패턴 상세하게 쓰는 법 1
    // 3. 외부 클래스 생성자는 내부 클래스를 생성자 매개변수로 받음 (=builder 객체를 받음)
    private BuilderVO(Builder builder) {
        this.name = builder.name; // 매개 값 받아서 저장 시킴
        this.age = builder.age; // 매개 값 받아서 저장 시킴
    }

    // 4. 외부에서 객체 생성을 요구하면 내부 클래스를 생성해서 반환함
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "BuilderVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 2. inner 클래스
    public static class Builder {
        private String name;
        private int age;

        // inner 클래스 생성자 제한
        private Builder() {}

        // 5. setter 메소드 : 멤버변수 값을 받아서 나 자신을 반환시키는 형태로 생성함
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        // 6. build 실행하면 외부 클래스 BuilderVO를 생성해서 반환함
        public BuilderVO build() {
            return new BuilderVO(this); // 나 자신을 BuilderVO 매개변수로 넣기
        }
    }
     */
}
