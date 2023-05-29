package capstone.capstone.controller;

import capstone.capstone.domain.Model;
import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://52.78.130.186:80")
@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //모델 목록의 데이터를 리턴
    @GetMapping("/model")
    public List<Model> getAllModel() {
        System.out.println("전체 모델 목록 반환");
        return modelService.getAllModel();
    }

    //모델을 저장
    @PostMapping("/model")
    public Model createModel(@RequestBody Model model) {
        System.out.println(model.getModel_name() + "모델 추가");
        return modelService.createModel(model);
    }

    @GetMapping("/{category}/model")
    public List<Model> getCategoryModel(@PathVariable String category){
        System.out.println(category + " 카테고리 " + "모델 목록 반환");
        return modelService.getCategoryModel(category);
    }

    @GetMapping("/{model}/{grade}")
    public double getFairPrice(@PathVariable String model, @PathVariable String grade){
        System.out.println(model + "의 " + grade + "등급 최근 거래가 반환");
        return modelService.getFairPrice(model, grade);
    }
}
