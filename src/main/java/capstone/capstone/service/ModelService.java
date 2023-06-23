package capstone.capstone.service;

import capstone.capstone.domain.Model;
import capstone.capstone.repository.ModelRepository;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Model> getAllModel() {
        return modelRepository.findAll();
    }

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    public List<Model> getCategoryModel(String category) {return modelRepository.findCategory(category);}

    public String getCategoryName(String model_name) { return modelRepository.findCategoryName(model_name);}

    public Double getMarketPrice(String model_name, String grade) { return postRepository.findFairPrice(model_name, grade); }
}