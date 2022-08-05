package dev.practice.order.infrastructure.order.payment;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.payment.PayProcessor;
import dev.practice.order.domain.order.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PayProcessorImpl implements PayProcessor {

    private final List<PaymentValidator> paymentValidatorList;
    private final List<PaymentApiCaller> paymentApiCallerList;

    @Override
    public void pay(Order order, OrderCommand.PaymentRequest paymentRequest) {

        // validation check
        paymentValidatorList.forEach(paymentValidator -> paymentValidator.validate(order, paymentRequest));

        // find payMethod
        PaymentApiCaller paymentApiCaller = routingApiCaller(paymentRequest);

        // pay
        paymentApiCaller.pay(paymentRequest);
    }

    private PaymentApiCaller routingApiCaller(OrderCommand.PaymentRequest paymentRequest) {
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(paymentRequest.getPayMethod()))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }

}
