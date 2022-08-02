package dev.practice.order.infrastructure.order.payment;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.payment.PayMethod;
import org.springframework.stereotype.Component;

@Component
public class NaverPayApiCaller implements PaymentApiCaller{

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.NAVER_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest paymentRequest) {
        //TODO
    }
}
