package dev.practice.order.domain.item;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Getter;

import java.util.List;

public class ItemInfo {

    @Getter
    public static class Main {
        private String itemToken;
        private Long partnerId;
        private String itemName;
        private Long itemPrice;
        private Item.Status status;
        private List<ItemOptionGroupInfo> itemOptionGroupInfoList;

        public Main(Item item, List<ItemOptionGroupInfo> itemOptionGroupInfoList) {
            this.itemToken = item.getItemToken();
            this.partnerId = item.getPartnerId();
            this.itemName = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.status = item.getStatus();
            this.itemOptionGroupInfoList = itemOptionGroupInfoList;
        }
    }

    @Getter
    public static class ItemOptionGroupInfo {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<ItemOptionInfo> itemOptionInfoList;

        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup, List<ItemOptionInfo> itemOptionInfoList) {
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.itemOptionInfoList = itemOptionInfoList;
        }
    }

    @Getter
    public static class ItemOptionInfo {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;

        public ItemOptionInfo(ItemOption itemOption) {
            this.ordering = itemOption.getOrdering();
            this.itemOptionName = itemOption.getItemOptionName();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }

}
