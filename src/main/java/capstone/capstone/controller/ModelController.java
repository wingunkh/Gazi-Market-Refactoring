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

    // 모델 전체 조회
    @GetMapping
    public ResponseEntity<List<Model>> findAll() {
        return ResponseEntity.ok(modelService.findAll());
    }

    // 해당 카테고리 내 모델 전체 조회
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Model>> findAllByCategoryCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(modelService.findAllByCategoryCategoryName(categoryName));
    }
}