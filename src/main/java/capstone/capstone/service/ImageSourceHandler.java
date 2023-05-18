package capstone.capstone.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Component
public class ImageSourceHandler {
    public String detectImageSource(MultipartFile imageFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
            String cameraMaker = bufferedImage.getProperty("Make").toString();
            String cameraModel = bufferedImage.getProperty("Model").toString();

            // 메타데이터에 카메라 제조사와 모델 정보가 있으면 직접 촬영한 이미지로 간주
            if(!StringUtils.isEmpty(cameraMaker) && !StringUtils.isEmpty(cameraModel)) {
                return "CAPTURED";
            } else { // 메타데이터에 카메라 제조사와 모델 정보가 없으면 다운로드 이미지로 간주
                return "DOWNLOADED";
            }

        } catch (IOException e) {
            // 이미지 파일을 읽는 중에 예외 발생
            e.printStackTrace();
        }
        return "???";
    }
}

