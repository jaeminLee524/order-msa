package dev.practice.order.domain.order.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_item_options")
public class OrderItemOption extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String itemOptionName;
    private Integer itemOptionPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroup orderItemOptionGroup;

    /** 생성 메서드 **/
    protected OrderItemOption(Integer ordering, String itemOptionName, Integer itemOptionPrice, OrderItemOptionGroup orderItemOptionGroup) {
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
        this.orderItemOptionGroup = orderItemOptionGroup;
    }

    public static OrderItemOption of(Integer ordering, String itemOptionName, Integer itemOptionPrice, OrderItemOptionGroup orderItemOptionGroup) {
        if (ordering==null) throw new InvalidParamException("OrderItemOptionGroup.ordering");
        if (StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("OrderItemOptionGroup.itemOptionName");
        if (itemOptionPrice==null) throw new InvalidParamException("OrderItemOptionGroup.itemOptionPrice");
        if (orderItemOptionGroup==null) throw new InvalidParamException("OrderItemOptionGroup.orderItemOptionGroup");

        return new OrderItemOption(ordering, itemOptionName, itemOptionPrice, orderItemOptionGroup);
    }
}
