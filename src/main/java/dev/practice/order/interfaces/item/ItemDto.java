package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.ItemCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ItemDto {

    @Getter
    @Setter
    public static class RegisterItemRequest {
        private String itemToken;
        private String itemName;
        private String itemPrice;
        private List<RegisterItemOptionGroupRequest> itemOptionGroupList;
    }

    @Getter
    @Setter
    public static class RegisterItemOptionGroupRequest {
        private Integer ordering;
        private String itemOptionGroupName;
        private List<RegisterItemOptionRequest> itemOptionRequestList;
    }

    @Getter
    @Setter
    public static class RegisterItemOptionRequest {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }



}
