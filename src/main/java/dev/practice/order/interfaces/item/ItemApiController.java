package dev.practice.order.interfaces.item;

import dev.practice.order.application.item.ItemFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.item.ItemCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items")
public class ItemApiController {

    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        // 1. extract partnerToken
        // 1. request -> command
        String partnerToken = request.getPartnerToken();
        ItemCommand.RegisterItemRequest itemCommand = itemDtoMapper.of(request);
        String itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        ItemDto.RegisterResponse response = itemDtoMapper.of(itemToken);

        return CommonResponse.success(response);
    }


}
