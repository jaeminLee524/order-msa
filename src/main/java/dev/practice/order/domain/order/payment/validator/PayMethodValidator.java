package dev.practice.order.domain.order.payment.validator;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

/**
 * 주문할때 선택한 결제 수단이 일치하는지 검증
 * @author jaemin
 * @version 1.0.0
 * 작성일 2022/08/02
**/
@Component
public class PayMethodValidator implements PaymentValidator{

    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if (!order.getPayMethod().equals(paymentRequest.getPayMethod().name())) {
            throw new InvalidParamException("주문 과정에서의 결제수단이 다릅니다");
        }
    }
}
