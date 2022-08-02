package dev.practice.order.domain.order.payment;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;

public interface PayProcessor {

    void pay(Order order, OrderCommand.PaymentRequest paymentRequest);
}
