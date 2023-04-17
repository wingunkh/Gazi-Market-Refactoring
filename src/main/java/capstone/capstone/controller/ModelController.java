package capstone.capstone.controller;

import capstone.capstone.domain.Model;
import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://52.78.130.186:80")
@CrossOrigin(origins = "http://ec2-52-78-130-186.ap-northeast-2.compute.amazonaws.com/:80")
@RestController
@RequestMapping("/api")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //모델 목록의 데이터를 리턴
    @GetMapping("/model")
    public List<Model> getAllModel() {
        return modelService.getAllModel();
    }

    //모델을 저장
    @PostMapping("/model")
    public Model createModel(@RequestBody Model model) {
        System.out.println(model);
        return modelService.createModel(model);
    }

    @GetMapping("/{category}/model")
    public List<Model> getCategoryModel(@PathVariable String category){
        return modelService.getCategoryModel(category);
    }
}
