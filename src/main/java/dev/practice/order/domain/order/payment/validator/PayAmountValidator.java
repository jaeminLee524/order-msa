package dev.practice.order.domain.order.payment.validator;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

/**
 * 주문 가격이 일치하는지 검증
 * @author jaemin
 * @version 1.0.0
 * 작성일 2022/08/02
**/
@Component
public class PayAmountValidator implements PaymentValidator{

    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if (!order.calculateTotalAmount().equals(paymentRequest.getAmount())) {
            throw new InvalidParamException("주문가격이 불일치합니다");
        }
    }
}
