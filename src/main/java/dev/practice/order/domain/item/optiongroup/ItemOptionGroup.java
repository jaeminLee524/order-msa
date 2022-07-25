package dev.practice.order.domain.item.optiongroup;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.option.ItemOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "item_option_groups")
@Entity
public class ItemOptionGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String itemOptionGroupName;

    /** Item 1:N ItemOptionGroup **/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /** ItemOptionGroup 1:N ItemOption **/
    @OneToMany(mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = new ArrayList<>();

    public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }

    /** 생성 메소드 **/
    public static ItemOptionGroup of(Item item, Integer ordering, String itemOptionGroupName) {
        if (item == null) throw new InvalidParamException("ItemOptionGroup.item");
        if (ordering == null) throw new InvalidParamException("ItemOptionGroup.ordering");
        if (StringUtils.isBlank(itemOptionGroupName)) throw new InvalidParamException("ItemOptionGroup.itemOptionGroupName");

        return new ItemOptionGroup(item, ordering, itemOptionGroupName);
    }
}
