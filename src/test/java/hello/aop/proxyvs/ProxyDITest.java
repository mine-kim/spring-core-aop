package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시, DI 예외 발생
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프 록시, 성공
@SpringBootTest //기본: CGLIB 설정
public class ProxyDITest {

    /**
     * JDK 동적 프록시는 인터페이스 기반, 대상 객체인 `MemberServiceImpl` 로 캐스팅 할 수 없다.
     * CGLIB 프록시는 구체클레스 기반, 대상 객체인 `MemberServiceImpl` 로 캐스팅 할 수 있다.
     *
     * CGLIB 구체 클래스 기반 프록시 문제점**
     * - 대상 클래스에 기본 생성자 필수
     * - 생성자 2번 호출 문제
     * - final 키워드 클래스, 메서드 사용 불가
     *
     * JDK 동적 프록시 문제점
     * - 대상 클래스 타입으로 주입할 때 문제
     */
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl calss={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
