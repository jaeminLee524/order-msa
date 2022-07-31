package dev.practice.order.domain.order;

import dev.practice.order.domain.order.orderitem.OrderItem;

import java.util.List;

public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrder registerOrder);
}
