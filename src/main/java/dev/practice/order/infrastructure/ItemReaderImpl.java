package dev.practice.order.infrastructure;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.ItemReader;
import dev.practice.order.infrastructure.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemReaderImpl implements ItemReader {

    private final ItemRepository itemRepository;


    @Override
    public Item getItemBy(String itemToken) {
        return itemRepository.findByItemToken(itemToken).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item) {
        return item.getItemOptionGroups().stream()
                .map(itemOptionGroup -> {
                    // create List<ItemInfo.itemOptionInfo>
                    List<ItemInfo.ItemOptionInfo> itemOptionInfoList = itemOptionGroup.getItemOptionList().stream()
                            .map(itemOption -> new ItemInfo.ItemOptionInfo(itemOption)).collect(Collectors.toList());

                    return new ItemInfo.ItemOptionGroupInfo(itemOptionGroup, itemOptionInfoList);
                }).collect(Collectors.toList());
    }
}
