package dev.practice.order.domain.order;

import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.payment.PayProcessor;
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
    private final PayProcessor payProcessor;

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

    @Transactional
    @Override
    public void paymentOrder(OrderCommand.PaymentRequest paymentCommand) {

        // getOrder
        String orderToken = paymentCommand.getOrderToken();
        Order order = orderReader.getOrder(orderToken);
        payProcessor.pay(order, paymentCommand);


        // validation check
            // 주문 상태 체크, 이미 결제된건

        // payProcessor

    }
}
