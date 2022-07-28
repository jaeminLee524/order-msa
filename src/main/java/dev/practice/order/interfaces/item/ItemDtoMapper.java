package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    // target method(Source source)
    @Mappings({@Mapping(target = "registerItemOptionGroupRequestList", source = "request.itemOptionGroupList")})
    ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

    @Mappings({@Mapping(target = "registerItemOptionRequestList", source = "request.itemOptionList")})
    ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

    ItemDto.RegisterResponse of(String itemToken);
}
