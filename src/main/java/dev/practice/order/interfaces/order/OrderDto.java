package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.payment.PayMethod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDto {

    /** request **/
    @Getter @Setter
    public static class RegisterOrderReq {
        @NotNull(message = "userId 는 필수값입니다")
        private Long userId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "receiverName 는 필수값입니다")
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private String receiverPhone;

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        private String receiverZipcode;

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        private String receiverAddress1;

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private String etcMessage;

        private List<RegisterOrderItemReq> orderItemList;
    }

    @Getter @Setter
    public static class RegisterOrderItemReq {
        @NotNull(message = "orderCount 는 필수값입니다")
        private Integer orderCount;

        @NotBlank(message = "itemToken 는 필수값입니다")
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        private Long itemPrice;

        private List<RegisterOrderItemOptionGroupReq> orderItemOptionGroupList;
    }

    @Getter @Setter
    public static class RegisterOrderItemOptionGroupReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private String itemOptionGroupName;

        private List<RegisterOrderItemOptionReq> orderItemOptionList;
    }

    @Getter @Setter
    public static class RegisterOrderItemOptionReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private Long itemOptionPrice;
    }

    @Getter @Setter
    public static class PaymentRequest {
        private String orderToken;
        private PayMethod payMethod;
        private Long amount;
        private String orderDescription;
    }

    /** response **/
    @Getter @Setter
    public static class RegisterResponse {
        private String orderToken;
    }

    @Getter @Setter
    public static class Main {
        private String orderToken;
        private Long userId;
        private String payMethod;
        private Long totalAmount;
        private DeliveryInfo deliveryInfo;
        private String orderedAt;
        private String status;
        private String statusDescription;
        private List<OrderItem> orderItemList;
    }

    @Getter @Setter
    public static class DeliveryInfo {
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessage;
    }

    @Getter @Setter
    public static class OrderItem {
        private Long partnerId;
        private Long itemId;
        private String itemName;
        private Long itemPrice;
        private Integer orderCount;
        private Long totalAmount;
        private String deliveryStatus;
        private String deliveryDescription;
        private List<OrderItemOptionGroup> itemOptionGroupList;
    }

    @Getter @Setter
    public static class OrderItemOptionGroup {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<OrderItemOption> itemOptionList;
    }

    @Getter @Setter
    public static class OrderItemOption {
        private Integer ordering;
        private String itemOptionName;
    }

}
