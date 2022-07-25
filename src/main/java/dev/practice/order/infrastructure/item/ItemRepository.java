package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
