package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * 리플렉션
 * - 클래스나 메서드의 메타정보를 사용해서 동적으로 호출하는 메서드를 변경할 수 있다.
 *
 * 주의
 * - 런타임에 동작하기 때문에 컴파일 시점에 오류를 잡을 수 없다.
 * - 때문에 잘 사용하지 않는다.
 */
@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        //클래스 메타 정보 획득
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //callA의 메서드 메타 정보 획득
        Method callA = classHello.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("result1={}",result1);

        //callB의 메서드 메타 정보 획득
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result1={}",result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 메타 정보 획득
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method callA = classHello.getMethod("callA");
        dynamicCall(callA, target);

        Method callB = classHello.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
