package dev.practice.order.domain.order;

import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.infrastructure.order.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final OrderInfoMapper orderInfoMapper;

    @Transactional
    @Override
    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        Order order = orderStore.store(registerOrder.toEntity());
        orderItemSeriesFactory.store(order, registerOrder);

        return order.getOrderToken();
    }

    @Override
    public OrderInfo.Main retrieveOrder(String orderToken) {
        Order order = orderReader.getOrder(orderToken);
        List<OrderItem> orderItemList = order.getOrderItemList();

        return orderInfoMapper.of(order, orderItemList);
    }
}
