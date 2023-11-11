package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * cglib
 * 자식 클래스를 동적으로 생성하기 때문에 기본 생성자가 필요
 * 클래스에 final키워드가 붙으면 상속이 불가능
 * 메서드에 final키워드가 붙으면 해당 메서드 오버라이딩 불가능
 */
@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        //cglib는 Enhancer를 사용하여 프록시를 생성
        Enhancer enhancer = new Enhancer();
        //cglib는 구체 클래스를 상속 받아서 프록시를 생성
        enhancer.setSuperclass(ConcreteService.class);
        //프록시에 적용할 실행 로직을 할당
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
