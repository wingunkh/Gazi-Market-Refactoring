package capstone.capstone.controller;


import capstone.capstone.domain.Categories;
import capstone.capstone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //카테고리 목록의 데이터를 리턴
    @GetMapping("/category")
    public List<Categories> getAllCategory() {
        return categoryService.getAllCategory();
    }

    //카테고리를 저장
    @PostMapping("/category")
    public Categories createCategory(@RequestBody Categories category) {
        return categoryService.createCategory(category);
    }
}
