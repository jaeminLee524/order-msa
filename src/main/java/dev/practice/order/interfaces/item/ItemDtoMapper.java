package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.ItemCommand;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    // target method(Source source)
    @Mappings({@Mapping(target = "registerItemOptionRequestList", source = "request.itemOptionGroupList")})
    ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

    @Mappings({@Mapping(target = "registerItemOptionRequestList", source = "request.itemOptionRequestList")})
    ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

}
