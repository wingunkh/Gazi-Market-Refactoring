package capstone.capstone.controller;

import capstone.capstone.domain.Models;
import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //모델 목록의 데이터를 리턴
    @GetMapping("/model")
    public List<Models> getAllModel() {
        return modelService.getAllModel();
    }

    //모델을 저장
    @PostMapping("/model")
    public Models createModel(@RequestBody Models model) {
        System.out.println(model);
        return modelService.createModel(model);
    }

    @GetMapping("/{category}/model")
    public List<Models> getCategoryModel(@RequestBody String category){
        return modelService.getCategoryModel(category);
    }
}
