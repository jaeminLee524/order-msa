package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;

    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }
}
