package capstone.capstone.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Component
public class ImageSourceHandler {
    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(convertedFile);
        return convertedFile;
    }

    public String detectImageSource(File imageFile) {
        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);

            if (imageReaders.hasNext()) {
                ImageReader reader = imageReaders.next();
                String formatName = reader.getFormatName();

                // EXIF 데이터가 없는 경우 다운로드 받은 이미지로 판별
                if (formatName == null) {
                    return "DOWNLOADED";
                }
            }
        } catch (IOException e) {
            // 이미지 파일을 읽을 수 없는 경우 예외 처리
            e.printStackTrace();
        }

        // EXIF 데이터가 있는 경우 직접 촬영한 이미지로 판별
        return "CAPTURED";
    }
}
