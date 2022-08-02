package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken).orElseThrow(EntityNotFoundException::new);
    }
}
