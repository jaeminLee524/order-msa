package dev.practice.order.infrastructure.item.itemOption;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemOptionStoreImpl implements ItemOptionStore {

    private final ItemOptionRepository itemOptionRepository;

    @Override
    public ItemOption store(ItemOption initItemOption) {
        return itemOptionRepository.save(initItemOption);
    }
}
