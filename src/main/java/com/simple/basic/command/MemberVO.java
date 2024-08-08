package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {

    @Pattern(regexp = "[a-zA-Z0-9]{8,}")
    private String id;
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}")
    private String pw;
}
