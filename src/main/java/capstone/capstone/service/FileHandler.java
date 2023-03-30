package capstone.capstone.service;

import capstone.capstone.domain.Picture;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileHandler {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;
    public List<Picture> saveToS3(
            Integer post_no,
            List<MultipartFile> multipartFiles
    ) throws Exception {
        // 반환할 Picture 객체 리스트
        List<Picture> PictureList = new ArrayList<>();

        // 빈 파일이 들어오면 빈 Picture 객체 리스트 반환
        if (multipartFiles.isEmpty()) {
            return PictureList;
        }

        // 업로드한 날짜를 파일명으로 지정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 로컬 폴더에 임시 저장하기 경로를 설정
        String path = new File("").getAbsolutePath() + "/" + "images/";

        File file = new File(path);
        // 저장할 위치에 디렉터리가 존재하지 않을 경우
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일 핸들링
        for (MultipartFile multipartFile : multipartFiles) {
            // 파일이 비어 있지 않을 때 작업해야 오류가 발생하지 않는다.
            if (!multipartFile.isEmpty()) {
                // jpeg, png, gif 파일들만 받아서 처리할 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 잘못된 파일이므로 반복문을 빠져나간다.
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일명이면 반복문을 빠져나간다.
                    else {
                        break;
                    }
                }
                // 파일명에 중복이 발생하지 않도록 나노 세컨드까지 동원하여 파일명 지정
                String new_file_name = System.nanoTime() + originalFileExtension;
                // 생성 후 Picture 객체 리스트에 추가
                Picture picture = Picture.builder()
                        .post_no(post_no)
                        .picture_location("여기에 S3 URL")
                        .build();
                PictureList.add(picture);

                file = new File(path  + new_file_name);
                try {
                    multipartFile.transferTo(file);
                    amazonS3Client.putObject(new PutObjectRequest(bucket, "images/" + current_date + new_file_name, file)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                } catch (Exception e) {
                    throw new RuntimeException();
                } finally {
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        return PictureList;
    }
}