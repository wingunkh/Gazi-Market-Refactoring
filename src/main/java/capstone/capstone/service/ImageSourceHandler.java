package capstone.capstone.service;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
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
            // OpenCV를 사용하여 이미지 로드
            Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());

            // 이미지가 성공적으로 로드되었는지 확인
            if (image.empty()) {
                return "???";
            }

            // 이미지를 그레이스케일로 변환
            Mat grayImage = new Mat();
            Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

            // Canny 엣지 감지 적용
            Mat edges = new Mat();
            Imgproc.Canny(grayImage, edges, 50, 150);

            // 감지된 엣지 개수 확인
            int edgeCount = Core.countNonZero(edges);

            // 엣지 개수에 기반하여 이미지 소스 판별
            if (edgeCount > 0) {
                return "CAPTURED";
            } else {
                return "DOWNLOADED";
            }
        } catch (Exception e) {
            // 이미지 처리 중에 예외 발생 시 예외 처리
            e.printStackTrace();
            return "???";
        }
    }
}
