package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.infrastructure.order.OrderInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {
    /** request **/
    @Mappings({@Mapping(target = "orderItemList", source = "request.orderItemList")})
    OrderCommand.RegisterOrder of(OrderDto.RegisterOrderReq request);

    @Mappings({@Mapping(target = "orderItemOptionGroupList", source = "request.orderItemOptionGroupList")})
    OrderCommand.RegisterOrderItem of(OrderDto.RegisterOrderItemReq request);

    @Mappings({@Mapping(target = "orderItemOptionList", source = "request.orderItemOptionList")})
    OrderCommand.RegisterOrderItemOptionGroup of(OrderDto.RegisterOrderItemOptionGroupReq request);

    OrderCommand.RegisterOrderItemOption of(OrderDto.RegisterOrderItemOptionReq request);

    OrderCommand.PaymentRequest of(OrderDto.PaymentRequest paymentRequest);


    /** response **/
    OrderDto.RegisterResponse of(String orderToken);

    @Mappings({@Mapping(target = "orderedAt", source = "mainResult.orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    OrderDto.Main of(OrderInfo.Main mainResult);

}
