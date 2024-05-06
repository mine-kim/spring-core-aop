package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV1Test {


    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external() {
        callServiceV1.external();
        log.info("target={}", callServiceV1.getClass());
    }

    @Test
    void internal() {
        // 외부에서 호출하는 경우는 proxy를 거치기 때문에 어드바이스가 적용
        callServiceV1.internal();
        log.info("target={}", callServiceV1.getClass());
    }
}