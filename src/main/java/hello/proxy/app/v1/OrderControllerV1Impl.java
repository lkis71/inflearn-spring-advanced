package hello.proxy.app.v1;

import lombok.RequiredArgsConstructor;

/**
 * 인터페이스와 구현 클래스를 활용한 스프링 빈 수동 등록
 */
@RequiredArgsConstructor
public class OrderControllerV1Impl implements OrderControllerV1 {

    private final OrderServiceV1 orderServiceV1;

    @Override
    public String request(String itemId) {
        orderServiceV1.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
