package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemStoreImpl implements ItemStore {

    private final ItemRepository itemRepository;

    @Override
    public Item store(Item initItem) {
        return itemRepository.save(initItem);
    }
}
