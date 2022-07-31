package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.orderitem.OrderItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, Long> {
}
