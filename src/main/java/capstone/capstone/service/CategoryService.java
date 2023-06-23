package capstone.capstone.service;

import capstone.capstone.domain.Category;
import capstone.capstone.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        System.out.println(category.getCategory_name() + " 카테고리 추가");
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        System.out.println("전체 카테고리 반환");
        return categoryRepository.findAll();
    }
}