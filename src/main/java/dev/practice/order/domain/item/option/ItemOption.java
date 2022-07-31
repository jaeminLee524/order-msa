package dev.practice.order.domain.item.option;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "item_options")
@Entity
public class ItemOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_option_id")
    private ItemOptionGroup itemOptionGroup;

    protected ItemOption(ItemOptionGroup itemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }

    /** 생성 메소드 **/
    public static ItemOption of(ItemOptionGroup itemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        if (itemOptionGroup == null) throw new InvalidParamException("ItemOption.itemOptionGroup");
        if (ordering == null) throw new InvalidParamException("ItemOption.ordering");
        if (StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("ItemOption.itemOptionName");
        if (itemOptionPrice == null) throw new InvalidParamException("ItemOption.itemOptionPrice");

        return new ItemOption(itemOptionGroup, ordering, itemOptionName, itemOptionPrice);
    }
}
