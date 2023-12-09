package capstone.capstone.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageSourceHandler {
    // 해당 이미지가 직접 촬영한 이미지인지 도용한 이미지인지 확인
    public String detectImageSource(MultipartFile imageFile) {
        try {
            // 이미지 파일의 메타데이터를 읽어온다.
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile.getInputStream());

            // EXIF 메타데이터를 읽어오며 존재하지 않을 시 null 값이 저장된다.
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory == null)
                return "DOWNLOADED";
            else
                return "CAPTURED";
        } catch (Exception e) {
            return "???";
        }
    }
}