package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {


    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external() {
        callServiceV2.external();
        log.info("target={}", callServiceV2.getClass());
    }

    @Test
    void internal() {
        // 외부에서 호출하는 경우는 proxy를 거치기 때문에 어드바이스가 적용
        callServiceV2.internal();
        log.info("target={}", callServiceV2.getClass());
    }
}