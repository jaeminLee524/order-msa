package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
