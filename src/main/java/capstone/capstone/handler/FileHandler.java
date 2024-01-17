package capstone.capstone.handler;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.coobird.thumbnailator.Thumbnails;

@Component
@RequiredArgsConstructor
public class FileHandler {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    private final AmazonS3Client amazonS3Client;

    public String saveToS3(MultipartFile multipartFile, String key) throws Exception {
        if (multipartFile.isEmpty()) {
            // 빈 파일이 들어오면 빈 문자열 반환
            return "";
        }

        // 로컬 디렉터리에 임시 저장하기 위한 경로 설정
        String path = new File("").getAbsolutePath() + "/" + "images/";
        File file = new File(path);

        // 저장할 경로에 디렉터리가 존재하지 않을 경우
        if (!file.exists()) {
            // 디렉터리 생성
            file.mkdirs();
        }

        // 파일 확장자명 설정
        String contentType = multipartFile.getContentType();
        String originalFileExtension = "";

        if (ObjectUtils.isEmpty(contentType)) {
            // 확장자명이 없으면 잘못된 파일이므로 빈 문자열 반환
            return "";
        } else if (contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if (contentType.contains("image/png")) {
            originalFileExtension = ".png";
        } else if (contentType.contains("image/gif")) {
            originalFileExtension = ".gif";
        } else {
            // 다른 확장자명이면 빈 문자열 반환
            return "";
        }

        // 파일명 설정 (중복이 발생하지 않도록 현재 날짜와 나노 세컨드까지 동원)
        String newFileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + System.nanoTime() + originalFileExtension;
        file = new File(path + newFileName);

        // EOF 에러를 방지하기 위해 파일 크기 변경
        Thumbnails.of(multipartFile.getInputStream())
                .size(800, 800)
                .toFile(file);

        try {
            // Amazon S3 Bucket에 파일 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucket, key + newFileName, file));
            System.gc();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (file.exists()) {
                // 로컬 디렉터리에 임시 저장한 파일 삭제
                file.delete();
            }
        }

        return amazonS3Client.getUrl(bucket, key + newFileName).toString();
    }

    public void deleteFromS3(String pictureLocation) {
        String key = pictureLocation.replace("https://gazi-market-bucket.s3.ap-northeast-2.amazonaws.com/", "");

        amazonS3Client.deleteObject(bucket, key);
    }
}