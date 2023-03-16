package capstone.capstone.service;

import capstone.capstone.domain.Picture;
import capstone.capstone.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;
    // private FileHandler fileHandler;

    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }
}
