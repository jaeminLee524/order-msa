package dev.practice.order.domain.order;

import dev.practice.order.infrastructure.order.OrderInfo;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder);

    OrderInfo.Main retrieveOrder(String orderToken);

    void paymentOrder(OrderCommand.PaymentRequest paymentCommand);
}
