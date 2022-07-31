package dev.practice.order.domain.order;

import dev.practice.order.domain.order.fragment.DeliveryFragment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderStore orderStore;
    private final OrderItemSeriesFactory orderItemSeriesFactory;

    @Transactional
    @Override
    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        // Order 생성
        Order order = orderStore.store(registerOrder.toEntity());

        return null;
    }
}
