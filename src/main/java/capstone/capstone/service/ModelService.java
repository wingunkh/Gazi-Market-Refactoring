package capstone.capstone.service;

import capstone.capstone.domain.Model;
import capstone.capstone.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Model findById(String modelName) {
        Optional<Model> optionalModel = modelRepository.findById(modelName);

        if (optionalModel.isPresent())
            return optionalModel.get();
        else
            throw new IllegalArgumentException("해당 모델이 존재하지 않습니다.");
    }

    public List<Model> findAllByCategoryCategoryName(String categoryName) {
        return modelRepository.findAllByCategoryCategoryName(categoryName);
    }
}