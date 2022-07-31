package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ItemDto {

    /** request **/
    @Getter @Setter
    public static class RegisterItemRequest {
        private String partnerToken;
        private String itemName;
        private String itemPrice;
        private List<RegisterItemOptionGroupRequest> itemOptionGroupList;
    }

    @Getter @Setter
    public static class RegisterItemOptionGroupRequest {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterItemOptionRequest> itemOptionList;
    }

    @Getter @Setter
    public static class RegisterItemOptionRequest {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }

    @Getter @Setter
    public static class ChangeStatusItemRequest {
        private String itemToken;
    }

    /** response **/
    @Getter @Setter
    public static class RegisterResponse {
        private String itemToken;
    }

    @Getter
    public static class Main {
        private String itemToken;
        private Long partnerId;
        private String itemName;
        private Long itemPrice;
        private Item.Status status;
        private List<ItemOptionGroupInfo> itemOptionGroupInfoList;
    }

    @Getter
    public static class ItemOptionGroupInfo {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<ItemOptionInfo> itemOptionInfoList;
    }

    @Getter
    public static class ItemOptionInfo {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }

}
