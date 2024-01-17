package capstone.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootConfiguration -> @SpringBootConfiguration -> @Configuration
// @SpringBootApplication -> @EnableAutoConfiguration & @ComponentScan
// 스프링 컨테이너란? 스프링 프레임워크의 핵심 컴포넌트로, 런타임 환경에서 스프링 빈의 생명주기를 관리하고 DI를 수행하는 역할을 한다. (Dependency Injection, 의존성 주입)
// 스프링 컨테이너를 IoC 컨테이너라고 부르기도 한다. (Inversion Of Control, 제어의 역전)
// 스프링 컨테이너는 @Configuration 어노테이션이 붙은 클래스를 설정(구성) 정보로 사용한다.
// 이때 스프링 컨테이너는 @ComponentScan 어노테이션에 의해 해당 패키지 내 @Component 및 그 파생 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
// 또한 스프링 컨테이너는 @Autowired 어노테이션이 붙은 필드 또는 메서드를 통해 스프링 빈 DI를 수행한다.
// DI를 통해 객체 간의 결합도를 낮추고 궁극적으로 유연한 애플리케이션을 만들 수 있다.
public class CapstoneApplication {
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}
}