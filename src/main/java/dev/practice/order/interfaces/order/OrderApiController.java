package dev.practice.order.interfaces.order;

import dev.practice.order.application.order.OrderFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.order.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderApiController {

    private OrderFacade orderFacade;
    private OrderDtoMapper orderDtoMapper;

    /**
     * 주문 등록
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
     **/
    @PostMapping("/init")
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request) {
        OrderCommand.RegisterOrder orderCommand = orderDtoMapper.of(request);
        String orderToken = orderFacade.registerOrder(orderCommand);
        OrderDto.RegisterResponse response = orderDtoMapper.of(orderToken);

        return CommonResponse.success(response);
    }

}
