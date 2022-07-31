package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.OrderCommand;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {
    /** request **/
    @Mappings({@Mapping(target = "orderItemList", source = "request.orderItemList")})
    OrderCommand.RegisterOrder of(OrderDto.RegisterOrderRequest request);

    @Mappings({@Mapping(target = "orderItemOptionGroupList", source = "request.orderItemOptionGroupList")})
    OrderCommand.RegisterOrderItem of(OrderDto.RegisterOrderItem request);

    @Mappings({@Mapping(target = "orderItemOptionList", source = "request.orderItemOptionList")})
    OrderCommand.RegisterOrderItemOptionGroup of(OrderDto.RegisterOrderItemOptionGroup request);

    OrderCommand.RegisterOrderItemOption of(OrderDto.RegisterOrderItemOption request);


    /** response **/
    OrderDto.RegisterResponse of(String orderToken);
}
