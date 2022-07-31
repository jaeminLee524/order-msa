package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderItemSeriesFactory;
import dev.practice.order.domain.order.item.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderItemSeriesFactoryImp implements OrderItemSeriesFactory {

    @Override
    public List<OrderItem> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        registerOrder.getOrderItemList().stream()
                .map()
        return null;
    }
}
