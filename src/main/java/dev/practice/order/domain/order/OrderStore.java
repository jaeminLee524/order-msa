package dev.practice.order.domain.order;

import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.orderitem.OrderItemOption;
import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order initOrder);

    OrderItem store(OrderItem initOrderItem);

    OrderItemOptionGroup store(OrderItemOptionGroup initOrderItemOptionGroup);

    void store(OrderItemOption initOrderItemOption);
}
