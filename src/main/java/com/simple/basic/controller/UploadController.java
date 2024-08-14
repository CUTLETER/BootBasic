package com.simple.basic.controller;

import com.simple.basic.command.UploadVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

    @Value("${project.upload.path}") // application.properties에 있는 키값 받아옴
    private String uploadPath; // 저 아래에 업로드 경로 넣어주기 위해 선언함

    // 폴더 생성 함수
    public String makeFolder() {
        String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM")); // 월별 폴더명을 위한 오늘 날짜

        File file = new File(uploadPath+ "/" +filepath); // 3번 월별 폴더 생성 처리
        if(file.exists()==false) { // 해당 폴더가 있으면 true 없으면 false
            file.mkdirs(); // 폴더 생성
        }
        return filepath;
    }

    @GetMapping("/upload")
    public String uploadView() {
        return "fileupload/upload";
    }

    // 단일 파일 업로드
    @PostMapping("/uploadOk")
    public String uploadOk(@RequestParam("file") MultipartFile file) { // String 타입 아님

        // 처리해야 될 3가지
        // 1. 브라우저별로 사용자의 전체 경로가 제목에 포함되는 경우가 있는데 제외 시켜야 함
        // 2. 동일한 파일에 대한 처리 필요함 (동일한 파일명으로 올라오면 기존 파일 덮어씌워짐)
        // 3. 1개의 폴더에 저장 가능한 파일 수는 제한적(=대략 6만개?) (월별로 폴더 생성해서 처리해야 함)

        //file.getBytes(); // Byte로 변환된 파일의 내용
        //file.getInputStream(); // 파일 내용을 inputStream으로 반환
        String originName = file.getOriginalFilename(); // 원본 파일명
        String fileName = originName.substring(originName.lastIndexOf("\\")+1); // 1번 문제 처리

        String filePath = makeFolder(); // 폴더명 얻어옴
        String uuid = UUID.randomUUID().toString(); // 랜덤 값

        long size = file.getSize(); // 파일의 크기
        String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 업로드 경로 + 월별 폴더명 + 랜덤값 + 파일명 = 전체 경로

        try{
            // File file = new File("파일명을 포함한 업로드 경로");
            File path = new File(savePath);
            file.transferTo(path); // 파일을 주어진 경로에 저장시킴

            // fileName, filePath, uuid 이 값을 나중에 DB에 저장해둘 예정
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fileupload/upload_ok";
    }

    // 다중 파일 업로드 multiple
    @PostMapping("/uploadOk2")
    public String uploadOk2(MultipartHttpServletRequest files) {

        List<MultipartFile> list = files.getFiles("file"); //폼태그의 name값

        for(MultipartFile file : list) { // 파일 개수만큼 반복됨
            String originName = file.getOriginalFilename(); // 원본 파일명
            String fileName = originName.substring(originName.lastIndexOf("\\")+1); // 1번 문제 처리

            String filePath = makeFolder(); // 폴더명 얻어옴
            String uuid = UUID.randomUUID().toString(); // 랜덤 값

            long size = file.getSize(); // 파일의 크기
            String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 업로드 경로 + 월별 폴더명 + 랜덤값 + 파일명 = 전체 경로

            try{
                // File file = new File("파일명을 포함한 업로드 경로");
                File path = new File(savePath);
                file.transferTo(path); // 파일을 주어진 경로에 저장시킴

                // fileName, filePath, uuid 이 값을 나중에 DB에 저장해둘 예정
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "fileupload/upload_ok";
    }


    // 복수 태그로 여러 파일 업로드 - 프로젝트 예제에서 사용할 업로드
    @PostMapping("/uploadOk3")
    public String uploadOk3(@RequestParam("file") List<MultipartFile> list) {

        // 업로드 전에 파일이 없는 태그 값은 지우고 다시 처리해야 함 - 파일 없을 땐 형체 없는 껍데기(파일명에 랜덤값만 부여된 껍데기)가 올라감
         list = list.stream().filter(a -> a.isEmpty() == false).collect(Collectors.toList());

        for(MultipartFile file : list) {
            System.out.println(file.isEmpty()); // 파일 첨부의 유무 확인
            String originName = file.getOriginalFilename(); // 원본 파일명
            String fileName = originName.substring(originName.lastIndexOf("\\")+1); // 1번 문제 처리

            String filePath = makeFolder(); // 폴더명 얻어옴
            String uuid = UUID.randomUUID().toString(); // 랜덤 값

            long size = file.getSize(); // 파일의 크기
            String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 업로드 경로 + 월별 폴더명 + 랜덤값 + 파일명 = 전체 경로

            try{
                // File file = new File("파일명을 포함한 업로드 경로");
                File path = new File(savePath);
                file.transferTo(path); // 파일을 주어진 경로에 저장시킴

                // fileName, filePath, uuid 이 값을 나중에 DB에 저장해둘 예정
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "fileupload/upload_ok";
    }

    // 비동기 업로드
    @PostMapping("/uploadOk4")
    @ResponseBody // 일반 컨트롤러에서 rest api처럼 쓰고 싶으면 붙이기
    public String uploadOk4(
//                          @RequestParam("file") MultipartFile file,
//                          @RequestParam("writer") String writer
            //  혹시 VO 타입으로 받고 싶다면?
                            UploadVO vo
                            ) { // RequestBody는 JSON 형식일 때만 붙일 것
//        System.out.println(file);
//        System.out.println(writer);
        System.out.println(vo.toString());
        MultipartFile file = vo.getFile();

        System.out.println(file.isEmpty()); // 파일 첨부의 유무 확인
        String originName = file.getOriginalFilename(); // 원본 파일명
        String fileName = originName.substring(originName.lastIndexOf("\\")+1); // 1번 문제 처리

        String filePath = makeFolder(); // 폴더명 얻어옴
        String uuid = UUID.randomUUID().toString(); // 랜덤 값

        long size = file.getSize(); // 파일의 크기
        String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 업로드 경로 + 월별 폴더명 + 랜덤값 + 파일명 = 전체 경로

        try{
            // File file = new File("파일명을 포함한 업로드 경로");
            File path = new File(savePath);
            file.transferTo(path); // 파일을 주어진 경로에 저장시킴

            // fileName, filePath, uuid 이 값을 나중에 DB에 저장해둘 예정
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }


}
