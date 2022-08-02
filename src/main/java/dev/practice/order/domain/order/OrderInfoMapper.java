package dev.practice.order.domain.order;

import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.orderitem.OrderItemOption;
import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;
import dev.practice.order.infrastructure.order.OrderInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderInfoMapper {

    @Mappings({
            @Mapping(target = "orderId", source = "order.id"),
            @Mapping(target = "deliveryInfo", source = "order.deliveryFragment"),
            @Mapping(target = "totalAmount", expression = "java(order.calculateTotalAmount())"),
            @Mapping(target = "status", expression = "java(order.getStatus().name())"),
            @Mapping(target = "statusDescription", expression = "java(order.getStatus().getDescription())")
    })
    OrderInfo.Main of(Order order, List<OrderItem> orderItemList);

    @Mappings({
            @Mapping(target = "itemOptionGroupList", source = "orderItem.orderItemOptionGroupList"),
            @Mapping(target = "totalAmount", expression = "java(orderItem.calculateTotalAmount())"),
            @Mapping(target = "deliveryStatus", expression = "java(orderItem.getDeliveryStatus().name())"),
            @Mapping(target = "deliveryDescription", expression = "java(orderItem.getDeliveryStatus().getDescription())")
    })
    OrderInfo.OrderItem of(OrderItem orderItem);

    @Mappings({@Mapping(target = "itemOptionList", source = "orderItemOptionGroup.orderItemOptionList")})
    OrderInfo.OrderItemOptionGroup of(OrderItemOptionGroup orderItemOptionGroup);

    OrderInfo.OrderItemOption of(OrderItemOption orderItemOption);

}
