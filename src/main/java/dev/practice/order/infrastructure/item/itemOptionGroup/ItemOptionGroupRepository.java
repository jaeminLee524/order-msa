package dev.practice.order.infrastructure.item.itemOptionGroup;

import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, Long> {
}
