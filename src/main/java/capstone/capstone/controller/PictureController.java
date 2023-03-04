package capstone.capstone.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PictureController {
    public static void main(String[] args) {
        Path filePath = Paths.get("/Users/khg/Desktop/capstone/image_test1/ggami.jpeg"); // 기존 파일 경로
        Path targetPath = Paths.get("/Users/khg/Desktop/capstone/image_test2/ggami.jpeg"); // 목표 파일 경로

        try {
            Path updatedPath = Files.move(filePath, targetPath); // 파일 경로 변경, 변경된 파일 경로 리턴
            System.out.println(updatedPath); // 변경된 파일 경로 출력
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
