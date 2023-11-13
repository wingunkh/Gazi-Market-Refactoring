package capstone.capstone.controller;

import capstone.capstone.domain.Category;
import capstone.capstone.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
// HTTP Request와 HTTP Response를 처리하는 클래스임을 명시
// @Controller에 @ResponseBody가 결합, 문자열과 JSON 등을 전송 가능
@RequiredArgsConstructor
// final 또는 @NonNull이 붙은 필드 값을 파라미터로 받는 생성자 추가
// 생성자가 하나일 경우 @Autowired 어노테이션을 생략 가능
@RequestMapping("/api/category")
// HTTP Request URL을 특정 클래스나 메서드에 매핑
public class CategoryController {
    private final CategoryService categoryService;
    // 생성자 주입의 장점
    // 1. 생성자는 객체를 생성할 때 딱 한 번만 호출되므로 불변성을 보장한다.
    // 2. 프레임워크 없이 순수한 자바 코드 단위 테스트 중 주입 데이터를 누락 시 실행 중 오류가 아닌 컴파일 오류가 발생한다.
    // 3. 필드에 final 키워드를 사용할 수 있다. 그러므로 생성자에서 혹시 값이 설정되지 않았을 경우 컴파일 오류가 발생한다.

    // 카테고리 저장
    @PostMapping
    // POST 메서드 : RequestBody를 통해 서버로 데이터를 전송, 주로 등록에 사용
    // POST 메서드는 멱등하지 않다.
    // 멱등성(Idempotent)이란? 연산을 여러 번 적용하더라도 결과가 달라지지 않는 성질
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    // 전체 카테고리 조회
    @GetMapping
    // GET 메서드 : 데이터 조회
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}