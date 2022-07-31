package dev.practice.order.interfaces.item;

import dev.practice.order.application.item.ItemFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items")
public class ItemApiController {

    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    /**
     * 물품 등록
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
    **/
    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        String partnerToken = request.getPartnerToken();
        ItemCommand.RegisterItemRequest itemCommand = itemDtoMapper.of(request);
        String itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        ItemDto.RegisterResponse response = itemDtoMapper.of(itemToken);

        return CommonResponse.success(response);
    }

    /**
     * 물품 상태 변경 - 판매 중
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
     **/
    @PostMapping("/change-on-sales")
    public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        String itemToken = request.getItemToken();
        itemFacade.changeOnSaleItem(itemToken);

        return CommonResponse.success("OK");
    }

    /**
     * 물품 상태 변경 - 판매 종료
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
     **/
    @PostMapping("/change-end-of-slaes")
    public CommonResponse changeEndOfItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        String itemToken = request.getItemToken();
        itemFacade.changeEndOfSaleItem(itemToken);

        return CommonResponse.success("OK");
    }


    /**
     * 물품 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
     **/
    @GetMapping("/{itemToken}")
    public CommonResponse retrieve(@PathVariable("itemToken") String itemToken) {
        ItemInfo.Main main = itemFacade.retrieveItemInfo(itemToken);
        ItemDto.Main response = itemDtoMapper.of(main);

        return CommonResponse.success(response);
    }
}
