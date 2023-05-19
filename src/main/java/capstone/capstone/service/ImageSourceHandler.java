package capstone.capstone.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageSourceHandler {
    public String detectImageSource(MultipartFile imageFile) {
        try {
            // 이미지 파일의 메타데이터를 읽어온다.
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile.getInputStream());
            System.out.println("metadata ===> " + metadata);

            // EXIF 메타 데이터를 읽어오며 존재하지 않을 시 null 값이 저장된다.
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            System.out.println("directory ===> " + directory);

            if (directory == null) {
                return "DOWNLOADED";
            } else {
                return "CAPTURED";
            }
        } catch (Exception e) {
            // 이미지 파일을 읽을 수 없거나 예외가 발생한 경우 예외 처리
            e.printStackTrace();
            return "???";
        }
    }
}