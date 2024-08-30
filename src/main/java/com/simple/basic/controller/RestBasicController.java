package com.simple.basic.controller;

import com.simple.basic.command.MemoVO;
import com.simple.basic.command.TestVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/*
@Controller와 @RestController의 근본적인 차이점은 @Controller의 역할은 Model 객체를 만들어 데이터를 담고 View를 찾는 것이지만
@RestController는 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송함
물론 @Controller와 @ResponseBody를 사용하여 만들 수 있지만
어노테이션 1개면 되는데 2개를 굳이?
*/

@RestController // Controller + ResponseBody의 합성어 (컨트롤러에서 요청이 들어온 곳으로 응답함)
public class RestBasicController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World"; // 원래는 화면의 경로를 적었지만 Rest API는 요청 보낸 곳으로 직접 응답함
    }

    @GetMapping("/hello2")
    public String[] hello2() {
        return new String[] {"홍","길","동"}; // 요청 보낸 곳으로 직접 응답!
    }


    // Get 방식의 요청 받기 - 일반 컨트롤러에서 받는 형식과 똑같은 방법으로 가능함 - 경로 속에 ? & 데이터 나열
    // 크롬 캐스트 확장 프로그램 '부메랑' 켜서 할 것
    // 예시 1) http://localhost:8181/getData?num=1&name=홍길동
    //    @GetMapping("/getData")
    //    public String getData(TestVO vo) {
    //        System.out.println(vo.toString());
    //        return "getData";
    //    }
    @GetMapping("/getData")
    public String getData(@RequestParam("num") int num, @RequestParam("name") String name) {
        System.out.println(num+", "+name);
        return "getData";
    }

    // PathVariable 방식의 요청 받기 - 경로 속에 {}/{} 데이터 나열
    // 예시 2) http://localhost:8181/getData2/1/홍길동
    @GetMapping("/getData2/{num}/{name}")
    public String getData2(@PathVariable("num") int num, @PathVariable("name") String name) {
        System.out.println(num+", "+name);
        return "SUCCESS";
    }

    // 반환을 JSON 형식으로 하려면 MAP 타입이나 VO를 쓰면 됨
    // 그러려면 Jackson-databind 라이브러리가 반드시 필요함 (스프링 부트에 포함돼 있음)
    // * 참고 : xml으로도 반환이 가능함
    @GetMapping("/returnData")
    public TestVO returnData() {
        return new TestVO(1, "서버에서의 반환", 20, "서울시");
    }

    @GetMapping("/returnData2")
    public Map<String, Object> returnData2() {
        Map<String, Object> map = new HashMap<>();
        map.put("num", 1);
        map.put("name", "홍길동");
        map.put("arr", Arrays.asList("a", "b", "c"));
        return map;
    }


    // Post 방식 - 소비자(사용자)와 제공자(서버) 간의 데이터를 주고 받는 규약이 정확하게 지켜져야 함!
    // Form 형식으로 데이터 전송하기 - 소비자 데이터를 Form 형식으로 반드시 만들어서 보내야 함!
    @PostMapping("/getForm")
    public String getForm(TestVO vo) {
        System.out.println(vo.toString());
        return "SUCCESS";
    }

    // JSON 형식으로 데이터 전송하기
    // @RequestBody - JSON 데이터를 자바 오브젝트로 변형해서 맵핑시켜줌
    // { "name" : "홍길동", "age" : 20, "addr" : "서울시" }
    @PostMapping("/getJSON")
    public String getJSON(@RequestBody TestVO vo) {
    // JSON 과 JAVA Object는 다른 거라 추가 작업이 필요함! = @RequestBody 적으면 JSON 데이터 없으면 FORM 데이터!
        System.out.println(vo.toString());
        return "SUCCESS";
    }

//    @PostMapping("/getJSON")
//    public String getJSON(@RequestBody Map<String, Object> map) { // map은 잘못된 데이터 들어올 수 있으니 웬만하면 VO 타입으로!
//        // JSON 과 JAVA Object는 다른 거라 추가 작업이 필요함! = @RequestBody 적으면 JSON 데이터 없으면 FORM 데이터!
//        System.out.println(map.toString());
//        return "SUCCESS";
//    }

    // @PutMapping(수정), @DeleteMapping(삭제) - Post 방식과 거의 유사함



    // Consumes - 이 타입으로 보내라
    // Produces - 이 타입으로 줄게
    // 기본 값은 JSON 타입! -> "application/json"
    @PostMapping(value= "/getResult", produces = "text/html;charset=UTF-8", consumes = "text/plain")
    public String getResult(@RequestBody String str) {
        System.out.println(str);
        return "<h3>문자열</h3>";
    }



    // 응답 문서 명확하게 작성하기!
    // ResponseEntity<데이터 타입>
    @CrossOrigin({"http://127.0.0.1:5500","http://localhost:5500", "http://localhost:3000"}) // CORS 에러 방지 - 다른 서버에서의 요청(VScode) 허용
    // @CrossOrigin("*") - 모든 서버에 대한 요청을 승인하기 때문에 요청 경로 잘 모르겠으면 써도 됨! 다만 굉장히 위험할 수 있음
    @PostMapping("/getEntity")
    public ResponseEntity<TestVO> getEntity(@RequestBody TestVO vv) {
        System.out.println("받은 데이터 vv : "+vv.toString()); // vv는 hello.html의 post 방식 예제

        TestVO vo = new TestVO(1,"홍길동",20,"서울시");
        // 1
        //ResponseEntity entity = new ResponseEntity(vo, HttpStatus.BAD_REQUEST); // HttpStatus.xxxxx -> status(상태값) 직접 명시 가능함!
        //return entity;

        // 2
        HttpHeaders header = new HttpHeaders(); // 헤더
        header.add("Authorization", "Bearer JSON WEB TOKEN"); // 키, 값
        header.add("Content-type", "application/json;charset=UTF-8"); // produces와 같은 표현
//      header.add("Access-Control-Allow-Origin", "http://example.com");

        ResponseEntity entity = new ResponseEntity(vo, header, HttpStatus.OK); // 데이터, 헤더, 상태값
        
        return entity;
    }


    // 요청 주소 : /api/v1/getData
    // 메소드 : get 방식
    // 요청 파라미터 : sno(숫자), name(문자)
    // 응답 파라미터 : MemoVO 타입
    // 헤더에 담을 내용 HttpStatus.OK
    // fetch API 사용헤서 클라이언트에 요청, 응답
    // 이 내용들을 담아서 REST API 만들기

    @GetMapping("/api/v1/getData")
    public ResponseEntity<MemoVO> getData3(@RequestParam("sno") int sno, @RequestParam("name") String name) {
        System.out.println(sno+", "+name);
        return new ResponseEntity<MemoVO>(new MemoVO(1L, "홍길동", "01013147564", null, "n"), HttpStatus.OK);
    }

    // 요청 주소 : /api/v1/getInfo
    // 메소드 : post 방식
    // 요청 파라미터 : MemoVO 타입
    // 응답 파라미터 : List<MemoVO> 타입
    // 헤더에 담을 내용 : HttpStatus.OK
    // fetch API 사용해서 클라이언트에 요청, 응답

    @PostMapping("/api/v1/getInfo")
    public ResponseEntity<List<MemoVO>> getInfo(@RequestBody @Valid MemoVO vo, BindingResult binding) { // 유효성 검증 추가
        if(binding.hasErrors()) {
            System.out.println("유효성 검증에 실패했습니다.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<MemoVO> list = new ArrayList<>();
        list.add(vo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
