package capstone.capstone.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

@Component
public class ImageSourceHandler {
    public String detectImageSource(MultipartFile imageFile) {
        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile.getInputStream());
            ImageReader imageReader = ImageIO.getImageReaders(imageInputStream).next();
            String formatName = imageReader.getFormatName();

            // 이미지 포맷 이름이 null이거나 빈 문자열인 경우 다운로드 받은 이미지로 판별
            if (StringUtils.isEmpty(formatName)) {
                return "DOWNLOADED";
            }

            // EXIF 메타데이터를 읽어옴
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile.getInputStream());
            System.out.println("metadata ===> " + metadata);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            System.out.println("directory ===> " + directory);
            System.out.println(directory.containsTag(ExifSubIFDDirectory.TAG_MAKE));
            if (directory != null && directory.containsTag(ExifSubIFDDirectory.TAG_MAKE)) {
                String make = directory.getString(ExifSubIFDDirectory.TAG_MAKE);
                System.out.println("make ===>" + make);

                // "Make" 필드가 존재하지 않는 경우 다운로드 받은 이미지로 판별
                if (StringUtils.isEmpty(make)) {
                    return "DOWNLOADED";
                }
            }
        } catch (Exception e) {
            // 이미지 파일을 읽을 수 없거나 예외가 발생한 경우 예외 처리
            e.printStackTrace();
        }

        // "Make" 필드가 존재하고 해당 값이 특정 값과 일치하는 경우 직접 촬영한 이미지로 판별
        return "CAPTURED";
    }
}