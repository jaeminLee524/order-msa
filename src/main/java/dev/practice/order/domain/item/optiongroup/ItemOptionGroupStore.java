package dev.practice.order.domain.item.optiongroup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface ItemOptionGroupStore {

    ItemOptionGroup store(ItemOptionGroup initItemOptionGroup);
}
