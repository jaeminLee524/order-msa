package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemReader;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderItemSeriesFactory;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.orderitem.OrderItemOption;
import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {

    private final ItemReader itemReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderItem> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        return registerOrder.getOrderItemList().stream()
                .map(registerOrderItem -> {
                    // orderItem 저장
                    Item item = itemReader.getItemBy(registerOrderItem.getItemToken());
                    OrderItem initOrderItem = registerOrderItem.toEntity(order, item);
                    OrderItem orderItem = orderStore.store(initOrderItem);

                    // orderItemOptionGroup 저장
                    registerOrderItem.getOrderItemOptionGroupList().forEach(registerOrderItemOptionGroup -> {
                        OrderItemOptionGroup initOrderItemOptionGroup = registerOrderItemOptionGroup.toEntity(orderItem);
                        OrderItemOptionGroup orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                        // orderItemOption 저장
                        registerOrderItemOptionGroup.getOrderItemOptionList().forEach(registerOrderItemOption -> {
                            OrderItemOption initOrderItemOption = registerOrderItemOption.toEntity(orderItemOptionGroup);
                            orderStore.store(initOrderItemOption);
                        });
                    });
                    return orderItem;
            }).collect(Collectors.toList());
    }

}
