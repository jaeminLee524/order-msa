package dev.practice.order.domain.order.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long partnerId;
    private Integer orderCount;
    private Long itemId;
    private String itemToken;
    private String itemName;
    private Long itemPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroup> orderItemOptionGroupList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Getter @RequiredArgsConstructor
    public enum DeliveryStatus {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    public Long calculateTotalAmount() {
        long itemOptionTotalAmount = orderItemOptionGroupList.stream()
                .mapToLong(orderItemOptionGroup -> orderItemOptionGroup.calculateTotalAmount())
                .sum();

        return (itemPrice + itemOptionTotalAmount) * orderCount;
    }

    /** 생성 매서드 **/
    protected OrderItem(Long partnerId, Integer orderCount, Long itemId, String itemToken, String itemName, Long itemPrice, Order order) {
        this.partnerId = partnerId;
        this.orderCount = orderCount;
        this.itemId = itemId;
        this.itemToken = itemToken;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.order = order;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    public static OrderItem of(Long partnerId, Integer orderCount, Long itemId, String itemToken, String itemName, Long itemPrice, Order order) {
        if (partnerId == null) throw new InvalidParamException("OrderItemLine.partnerId");
        if (orderCount == null) throw new InvalidParamException("OrderItemLine.orderCount");
        if (itemId == null) throw new InvalidParamException("OrderItemLine.itemId");
        if (StringUtils.isEmpty(itemToken)) throw new InvalidParamException("OrderItemLine.itemToken");
        if (StringUtils.isEmpty(itemName)) throw new InvalidParamException("OrderItemLine.itemName");
        if (itemPrice == null) throw new InvalidParamException("OrderItemLine.itemPrice");
        if (order == null) throw new InvalidParamException("OrderItemLine.order");

        return new OrderItem(partnerId, orderCount, itemId, itemToken, itemName, itemPrice, order);
    }
}
