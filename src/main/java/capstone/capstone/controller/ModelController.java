package capstone.capstone.controller;

import capstone.capstone.domain.Model;
import capstone.capstone.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;

    // 모델 저장
    @PostMapping
    public ResponseEntity<Model> save(@RequestBody Model model) {
        return ResponseEntity.ok(modelService.save(model));
    }

    // 전체 모델 조회
    @GetMapping
    public ResponseEntity<List<Model>> findAll() {
        return ResponseEntity.ok(modelService.findAll());
    }

    // 해당 카테고리의 모델 조회
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Model>> findByCategoryCategoryName(@PathVariable String categoryName){
        return ResponseEntity.ok(modelService.findByCategoryCategoryName(categoryName));
    }
}