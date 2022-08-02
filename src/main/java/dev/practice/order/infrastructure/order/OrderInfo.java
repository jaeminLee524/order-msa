package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.orderitem.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderInfo {

    @Getter @Setter
    public static class Main {
        private Long orderId;
        private String orderToken;
        private Long userId;
        private String payMethod;
        private Long totalAmount;
        private DeliveryInfo deliveryInfo;
        private ZonedDateTime orderedAt;
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
        private String itemToken;
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
        private Long itemOptionPrice;
    }

}
