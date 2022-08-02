package dev.practice.order.domain.order.payment;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

/**
 * 중복 결제 체크
 * @author jaemin
 * @version 1.0.0
 * 작성일 2022/08/02
**/
@Component
public class PayStatusValidator implements PaymentValidator{

    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        // order 상태값이 INIT 이 아닌 경우 에러 메시지
        if (order.isAlreadyPaymentComplete()) throw new InvalidParamException("이미 결제완료된 주문입니다");
    }
}
