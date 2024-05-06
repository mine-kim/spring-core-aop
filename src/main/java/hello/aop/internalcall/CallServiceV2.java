package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //private final ApplicationContext applicationContext; //
    private final ObjectProvider<CallServiceV2> callServiceProvider;  // 지연 호출

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
        //CallServiceV2 callServiceV2 =applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callService = callServiceProvider.getObject();
        callService.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
