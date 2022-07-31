package dev.practice.order.domain.order;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;
import lombok.Getter;

import java.util.List;

public class OrderCommand {

    @Getter
    public static class RegisterOrder {
        private Long userId;
        private String payMethod;
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessgae;
        private List<RegisterOrderItem> orderItemList;

        public Order toEntity() {
            DeliveryFragment deliveryFragment =
                    DeliveryFragment.of(receiverName, receiverPhone, receiverZipcode, receiverAddress1, receiverAddress2, etcMessgae);

            return Order.of(this.userId, this.payMethod, deliveryFragment);
        }
    }

    @Getter
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

    @Getter
    private static class RegisterOrderItemOptionGroup {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterOrderItemOption> orderItemOptionList;

        public OrderItemOptionGroup toEntity(OrderItem orderItem) {
            return OrderItemOptionGroup.of(ordering,itemOptionGroupName, orderItem);
        }

    }

    @Getter
    public static class RegisterOrderItemOption {
        private Integer ordering;
        private String itemOptionName;
        private Integer itemOptionPrice;

        public OrderItemOption of(OrderItemOptionGroup orderItemOptionGroup) {
            return OrderItemOption.of(ordering, itemOptionName, itemOptionPrice, orderItemOptionGroup);
        }
    }
}
