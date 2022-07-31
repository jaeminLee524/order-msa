package dev.practice.order.domain.order.orderitem;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_item_option_groups")
public class OrderItemOptionGroup extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String itemOptionGroupName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @OneToMany(mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderItemOption> orderItemOptionList;

    /**
     * 옵션 그룹 총 가격
     **/
    public Long calculateTotalAmount() {
        return orderItemOptionList.stream()
                .mapToLong(orderItemOption -> orderItemOption.getItemOptionPrice())
                .sum();
    }

    protected OrderItemOptionGroup(Integer ordering, String itemOptionGroupName, OrderItem orderItem) {
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
        this.orderItem = orderItem;
    }

    /** 생성 메서드 **/
    public static OrderItemOptionGroup of(Integer ordering, String itemOptionGroupName, OrderItem orderItem) {
        if (ordering==null) throw new InvalidParamException("OrderItemOptionGroup.ordering");
        if (StringUtils.isBlank(itemOptionGroupName)) throw new InvalidParamException("OrderItemOptionGroup.itemOptionGroupName");
        if (orderItem==null) throw new InvalidParamException("OrderItemOptionGroup.orderItem");

        return new OrderItemOptionGroup(ordering, itemOptionGroupName, orderItem);
    }
}
