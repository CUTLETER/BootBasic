package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidVO {

    /*
    @NotNull - null 제외
    @NotEmpty - null 제외, 공백 제외
    @NotBlank - null 제외, 공백 제외, 화이트 스페이스 제외
    @Pattern(regexp = ) - 정규 표현식으로 유효성 검사
    @Email - 이메일 타입이어야 함
    @Max, @Min - 숫자를 사용하는 필드를 검증하는데 사용 / @Size - 문자열, 배열 등의 크기를 검증하는데 사용
    @Max(value = ) - 해당 값이 주어진 값보다 크지 않은지 (value 이하의 값만 허용)
    @Min(value = ) - 해당 값이 주어진 값보다 작지 않은지 (value 이상의 값만 허용)
    @Size(min=, max=) - 길이를 제한할 때
    기타 등등
    */

    @NotBlank // (message = "이름은 필수로 입력해 주세요.")
    private String name;
    @NotNull(message = "급여는 필수로 입력해 주세요.")
    private Integer salary; // wrapper 타입으로 기재할 것! 값이 비어 있으면 null값으로 자동 바인딩 됨! int는 0 뿐이라 공백 받을 시 오류남!
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "연락처는 -(하이픈)을 넣어서 입력해 주세요.")
    private String phone;
    @NotBlank
    @Email(message = "이메일 형식이어야 합니다.") // 공백은 기본으로 통과시킴 -> 막을 거면 NotBlank 같이 쓰기
    private String email;
}
