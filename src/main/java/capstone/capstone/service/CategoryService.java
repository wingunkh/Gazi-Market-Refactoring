package capstone.capstone.service;

import capstone.capstone.domain.Categories;
import capstone.capstone.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Categories> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Categories createCategory(Categories category) {
        return categoryRepository.save(category);
    }
}
