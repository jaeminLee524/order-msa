package dev.practice.order.domain.order;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.orderitem.OrderItem;
import dev.practice.order.domain.order.orderitem.OrderItemOption;
import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrderCommand {

    @Getter @Builder
    public static class RegisterOrder {
        private Long userId;
        private String payMethod;
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessage;
        private List<RegisterOrderItem> orderItemList;

        public Order toEntity() {
            DeliveryFragment deliveryFragment =
                    DeliveryFragment.of(receiverName, receiverPhone, receiverZipcode, receiverAddress1, receiverAddress2, etcMessage);

            return Order.of(this.userId, this.payMethod, deliveryFragment);
        }
    }

    @Getter @Builder
    public static class RegisterOrderItem {
        private Integer orderCount;
        private String itemToken;
        private String itemName;
        private Long itemPrice;
        private List<RegisterOrderItemOptionGroup> orderItemOptionGroupList;


        public OrderItem toEntity(Order order, Item item) {
            return OrderItem.of(item.getPartnerId(), orderCount, item.getId(), item.getItemToken(), item.getItemName(), item.getItemPrice(), order);
        }

    }

    @Getter @Builder
    public static class RegisterOrderItemOptionGroup {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterOrderItemOption> orderItemOptionList;

        public OrderItemOptionGroup toEntity(OrderItem orderItem) {
            return OrderItemOptionGroup.of(ordering,itemOptionGroupName, orderItem);
        }

    }

    @Getter @Builder
    public static class RegisterOrderItemOption {
        private Integer ordering;
        private String itemOptionName;
        private Integer itemOptionPrice;

        public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
            return OrderItemOption.of(ordering, itemOptionName, itemOptionPrice, orderItemOptionGroup);
        }
    }
}
