package capstone.capstone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PostApprovalController {
    // 웹의 게시글 승인을 눌렀을 때 승인 대기 목록을 전송해야 한다.
}