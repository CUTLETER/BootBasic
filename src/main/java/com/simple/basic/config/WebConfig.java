package com.simple.basic.config;

import com.simple.basic.command.TestVO;
import com.simple.basic.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 설정 파일
//@PropertySource("classpath:/hello.properties") // 특정 properties 파일을 참조받고 싶다면?
// 여러 개의 properties 파일을 참조 받고 싶다면?
// @PropertySources({
//        @PropertySource("classpath:application.properties"),
//        @PropertySource("classpath:database.properties")
//})


public class WebConfig implements WebMvcConfigurer {

//    @Value("${server.port}") // ("${키값}") , application.properties 파일의 키값을 읽어서 받아옴
//    String port;
//
//    @Value("${hello}")
//    String hello;
//
//    @Value("${bye}")
//    String bye;
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    // 자바 코드로 bean 생성하기
//    @Bean
//    public TestVO testVO() {
//        return  new TestVO(); // bean 등록
//    }
//
//
//    @Bean // 스프링이 이 코드를 실행시켜서 리턴에 담기는 값을 bean으로 등록시킴
//    public void test() {
////        System.out.println("스프링 설정 파일 실행됨");
////
////        int result = applicationContext.getBeanDefinitionCount();
////        System.out.println("context 안에 bean의 개수 : "+result); // 157개
////
////        HomeController home = applicationContext.getBean(HomeController.class);
////        System.out.println("context 안에 HomeController : "+home);
//
//        TestVO vo = applicationContext.getBean(TestVO.class);
//        System.out.println("context 안에 testVO bean : "+vo);
//
//        System.out.println("application.properties의 server.port 값 : "+port);
//        System.out.println("application.properties의 hello 값 : "+hello);
//        System.out.println("application.properties의 bye 값 : "+bye);
//    }



}
