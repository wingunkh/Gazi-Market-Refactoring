package capstone.capstone.controller;

import capstone.capstone.domain.Models;
import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // CORS 문제를 해결하기 위한 어노테이션
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api")
public class ModelController {
    @Autowired // 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    private ModelService modelService;

    //Service를 호출해서 글 목록의 데이터를 리턴하는 메소드
    @GetMapping("/model") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<Models> getAllModel() {
        return modelService.getAllModel();
    }

    //Service를 호출해서 글을 저장하는 메소드
    @PostMapping("/model") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public Models createModel(@RequestBody Models model) {
        System.out.println(model);
        return modelService.createModel(model);
    }
}
