package dev.practice.order.domain.item;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemOptionGroupStore itemOptionGroupStore;

    private final ItemOptionStore itemOptionStore;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        // 1. get partnerId
        Partner partner = partnerReader.getPartner(partnerToken);
        Long partnerId = partner.getId();

        // 2. item store
        Item initItem = command.toEntity(partnerId);
        Item item = itemStore.store(initItem);

        // 3. itemOptionGroup + itemOption Store
        command.getRegisterItemOptionRequestList().forEach(registerItemOptionGroupRequest -> {
            // ItemOptionGroup store
            ItemOptionGroup initItemOptionGroup =
                    ItemOptionGroup.of(
                            item,
                            registerItemOptionGroupRequest.getOrdering(),
                            registerItemOptionGroupRequest.getItemOptionGroupName()
                    );
            ItemOptionGroup itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

            // ItemOption store
            registerItemOptionGroupRequest.getRegisterItemOptionRequestList().forEach(registerItemOptionRequest -> {
                ItemOption initOption =
                        ItemOption.of(
                                itemOptionGroup,
                                registerItemOptionRequest.getOrdering(),
                                registerItemOptionRequest.getItemOptionName(),
                                registerItemOptionRequest.getItemOptionPrice());
                itemOptionStore.store(initOption);
            });

        });

        // return itemToken
        return item.getItemToken();
    }

    @Override
    public void changeOnSale(String itemToken) {

    }

    @Override
    public void changeEndOfSale(String itemToken) {

    }

    @Override
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return null;
    }
}
