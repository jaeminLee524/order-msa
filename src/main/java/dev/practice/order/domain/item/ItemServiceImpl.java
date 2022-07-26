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
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    private final ItemReader itemReader;

    @Transactional
    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        // 1. get partnerId
        Partner partner = partnerReader.getPartner(partnerToken);
        Long partnerId = partner.getId();

        // 2. item store
        Item initItem = command.toEntity(partnerId);
        Item item = itemStore.store(initItem);

        // 3. itemOptionGroup + itemOption Store
//        command.getRegisterItemOptionRequestList().forEach(registerItemOptionGroupRequest -> {
//            // ItemOptionGroup store
//            ItemOptionGroup initItemOptionGroup =
//                    ItemOptionGroup.of(
//                            item,
//                            registerItemOptionGroupRequest.getOrdering(),
//                            registerItemOptionGroupRequest.getItemOptionGroupName()
//                    );
//            ItemOptionGroup itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);
//
//            // ItemOption store
//            registerItemOptionGroupRequest.getRegisterItemOptionRequestList().forEach(registerItemOptionRequest -> {
//                ItemOption initOption =
//                        ItemOption.of(
//                                itemOptionGroup,
//                                registerItemOptionRequest.getOrdering(),
//                                registerItemOptionRequest.getItemOptionName(),
//                                registerItemOptionRequest.getItemOptionPrice());
//                itemOptionStore.store(initOption);
//            });
//
//        });

        /**
         * 기존 복잡한 객체의 생성과 규칙 설정 -> ItemOptionSeriesFactory에게 위임
         * service 영역의 추상화 향상
         * OCP(확장에는 열려있고 변경에는 닫혀있는) 만족
         * domain layer 보고 비즈니스의 흐름을 파악할 수 있음
         **/
        itemOptionSeriesFactory.store(command, item);

        // return itemToken
        return item.getItemToken();
    }

    @Override
    public void changeOnSale(String itemToken) {
        Item item = itemReader.getItemBy(itemToken);
        item.changeOnSales();
    }

    @Override
    public void changeEndOfSale(String itemToken) {
        Item item = itemReader.getItemBy(itemToken);
        item.changeEndOfSales();
    }

    @Override
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        return null;
    }
}
