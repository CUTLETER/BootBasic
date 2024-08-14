package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadVO {
    //formData.append("file", data);
    //formData.append("writer", "홍길동");
    //동일하게
    private MultipartFile file;
    private String writer;

}
