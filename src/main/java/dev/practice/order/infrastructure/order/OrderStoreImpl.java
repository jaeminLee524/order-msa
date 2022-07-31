package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.orderitem.OrderItemOption;
import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemOptionGroupRepository orderItemOptionGroupRepository;
    private final OrderItemOptionRepository orderItemOptionRepository;

    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderItem store(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup) {
        return orderItemOptionGroupRepository.save(orderItemOptionGroup);
    }

    @Override
    public void store(OrderItemOption orderItemOption) {
        orderItemOptionRepository.save(orderItemOption);
    }
}
