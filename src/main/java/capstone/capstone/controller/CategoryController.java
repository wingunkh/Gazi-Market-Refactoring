package capstone.capstone.controller;

import capstone.capstone.domain.Category;
import capstone.capstone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 카테고리 저장
    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        System.out.println(category.getCategory_name() + "카테고리 저장");
        return categoryService.createCategory(category);
    }

    // 전체 카테고리 목록 리턴
    @GetMapping("/category")
    public List<Category> getAllCategory() {
        System.out.println("전체 카테고리 목록 반환");
        return categoryService.getAllCategory();
    }
}