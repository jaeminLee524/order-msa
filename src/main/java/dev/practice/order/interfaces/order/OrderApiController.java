package dev.practice.order.interfaces.order;

import dev.practice.order.application.order.OrderFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.infrastructure.order.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderReq request) {
        OrderCommand.RegisterOrder orderCommand = orderDtoMapper.of(request);
        String orderToken = orderFacade.registerOrder(orderCommand);
        OrderDto.RegisterResponse response = orderDtoMapper.of(orderToken);

        return CommonResponse.success(response);
    }

    /**
     * 주문 정보 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/31
     **/
    @PostMapping("/{orderToken}")
    public CommonResponse retrieveOrder(@PathVariable("orderToken") String orderToken) {
        OrderInfo.Main orderResult = orderFacade.retrieveOrder(orderToken);
        OrderDto.Main response = orderDtoMapper.of(orderResult);

        return CommonResponse.success(response);
    }

    /**
     * 주문 결제
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/08/02
     **/
    @PostMapping("/payment-order")
    public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest paymentRequest) {
        OrderCommand.PaymentRequest paymentCommand = orderDtoMapper.of(paymentRequest);
        orderFacade.paymentOrder(paymentCommand);

        return CommonResponse.success("OK");
    }
}
