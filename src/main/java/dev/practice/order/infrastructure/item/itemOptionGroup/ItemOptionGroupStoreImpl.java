package dev.practice.order.infrastructure.item.itemOptionGroup;

import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemOptionGroupStoreImpl implements ItemOptionGroupStore {

    private final ItemOptionGroupRepository itemOptionGroupRepository;

    @Override
    public ItemOptionGroup store(ItemOptionGroup initItemOptionGroup) {
        return itemOptionGroupRepository.save(initItemOptionGroup);
    }
}
