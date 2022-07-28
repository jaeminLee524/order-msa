package dev.practice.order.domain.order;

import dev.practice.order.common.exception.IllegalStatusException;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.item.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    private static final String ORDER_PREFIX = "ord_";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderToken;
    private Long userId;
    private String payMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Embedded
    private DeliveryFragment deliveryFragment;

    @Enumerated(EnumType.STRING)
    private Status status;

    private ZonedDateTime orderedAt;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품 가격 = (상품 가격 + 상품 옵션 가격) * 주문 개수
     */
    public Long calculateTotalAmount() {
        return this.orderItemList.stream()
                .mapToLong(orderItem -> orderItem.calculateTotalAmount())
                .sum();
    }

    public void orderComplete() {
        if(this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.ORDER_COMPLETE;
    }

    protected Order(Long userId, String payMethod, DeliveryFragment deliveryFragment) {
        this.orderToken = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.userId = userId;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderedAt = ZonedDateTime.now();
        this.status = Status.INIT;
    }

    /** 생성 메서드 **/
    public static Order of(Long userId, String payMethod, DeliveryFragment deliveryFragment) {
        if (userId==null) throw new InvalidParamException("Order.userId");
        if (StringUtils.isEmpty(payMethod)) throw new InvalidParamException("Order.payMethod");
        if (deliveryFragment==null) throw new InvalidParamException("Order.deliveryFragment");

        return new Order(userId, payMethod, deliveryFragment);
    }
}
