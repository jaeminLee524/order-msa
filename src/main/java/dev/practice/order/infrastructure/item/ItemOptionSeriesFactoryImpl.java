package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemOptionSeriesFactory;
import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.infrastructure.item.itemOption.ItemOptionRepository;
import dev.practice.order.infrastructure.item.itemOptionGroup.ItemOptionGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {

    private final ItemOptionGroupRepository itemOptionGroupRepository;
    private final ItemOptionRepository itemOptionRepository;

    @Transactional
    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {

        // 옵션 선택이없는 기본 아이템
        List<ItemCommand.RegisterItemOptionGroupRequest> itemOptionGroupRequestList = command.getRegisterItemOptionGroupRequestList();
        if (CollectionUtils.isEmpty(itemOptionGroupRequestList)) return Collections.emptyList();

        return itemOptionGroupRequestList.stream()
                .map(itemOptionGroupRequest -> {
                    ItemOptionGroup initItemOptionGroup = itemOptionGroupRequest.toEntity(item);
                    ItemOptionGroup itemOptionGroup = itemOptionGroupRepository.save(initItemOptionGroup);

                    itemOptionGroupRequest.getRegisterItemOptionRequestList().forEach(itemOptionRequest -> {
                        ItemOption initItemOption = itemOptionRequest.toEntity(itemOptionGroup);
                        itemOptionRepository.save(initItemOption);
                    });

                    return itemOptionGroup;
                }).collect(Collectors.toList());

    }
}
