package capstone.capstone.service;

import capstone.capstone.domain.Models;
import capstone.capstone.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;
    public List<Models> getAllModel() {
        return modelRepository.findAll();
    }

    public Models createModel(Models model) {
        return modelRepository.save(model);
    }
}
