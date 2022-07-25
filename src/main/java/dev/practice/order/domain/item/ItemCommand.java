package dev.practice.order.domain.item;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Getter;

import java.util.List;

public class ItemCommand {

    @Getter
    public static class RegisterItemRequest {
        private String itemName;
        private Long itemPrice;
        private List<RegisterItemOptionGroupRequest> registerItemOptionRequestList;

        public Item toEntity(Long partnerId) {
            return Item.of(partnerId, itemName, itemPrice);
        }
    }

    @Getter
    public static class RegisterItemOptionGroupRequest {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterItemOptionRequest> registerItemOptionRequestList;

        public ItemOptionGroup toEntity(Item item) {
            return ItemOptionGroup.of(item, ordering, itemOptionGroupName);
        }
    }

    @Getter
    public static class RegisterItemOptionRequest {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;


        public ItemOption toEntity(ItemOptionGroup itemOptionGroup) {
            return ItemOption.of(itemOptionGroup, ordering, itemOptionName, itemOptionPrice);
        }
    }

}
