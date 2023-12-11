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
    public ResponseEntity<Model> saveModel(@RequestBody Model model) {
        return ResponseEntity.ok(modelService.saveModel(model));
    }

    // 모델 전체 조회
    @GetMapping
    public ResponseEntity<List<Model>> findAllModels() {
        return ResponseEntity.ok(modelService.findAllModels());
    }

    // 해당 카테고리 내 모델 전체 조회
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Model>> findAllModelsByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(modelService.findAllModelsByCategoryName(categoryName));
    }
}