package capstone.capstone.controller;

import capstone.capstone.domain.Model;
import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class ModelController {
    @Autowired
    private ModelService modelService;

    // 모든 모델 목록 리턴
    @GetMapping("/model")
    public List<Model> getAllModel() {
        System.out.println("전체 모델 목록 반환");
        return modelService.getAllModel();
    }

    // 모델 저장
    @PostMapping("/model")
    public Model createModel(@RequestBody Model model) {
        System.out.println(model.getModel_name() + "모델 추가");
        return modelService.createModel(model);
    }

    // 해당 카테고리의 모델 목록 리턴
    @GetMapping("/{category}/model")
    public List<Model> getCategoryModel(@PathVariable String category){
        System.out.println(category + " 카테고리 " + "모델 목록 반환");
        return modelService.getCategoryModel(category);
    }

    // 해당 모델의 해당 등급의 최근 거래가 반환
    @GetMapping("/{model}/{grade}")
    public double getFairPrice(@PathVariable String model, @PathVariable String grade){
        System.out.println(model + "의 " + grade + "등급 최근 거래가 반환");
        return modelService.getFairPrice(model, grade);
    }
}