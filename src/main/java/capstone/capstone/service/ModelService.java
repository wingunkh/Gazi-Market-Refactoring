package capstone.capstone.service;

import capstone.capstone.domain.Model;
import capstone.capstone.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public Model findByModelName(String modelName) {
        return modelRepository.findByModelName(modelName);
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public List<Model> findByCategoryCategoryName(String categoryName) {
        return modelRepository.findByCategoryCategoryName(categoryName);
    }
}