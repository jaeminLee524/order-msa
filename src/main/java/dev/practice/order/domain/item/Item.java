package dev.practice.order.domain.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "items")
@Entity
public class Item extends AbstractEntity {
    private static final String PREFIX_ITEM = "item_";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken; // 식별자와 동등한 역할의 대체키
    private Long partnerId;
    private String itemName;
    private Long itemPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    /** Item 1:N ItemOptionGroup **/
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    protected Item(Long partnerId, String itemName, Long itemPrice) {
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.partnerId = partnerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }

    /** 생성 메소드 **/
    public static Item of(Long partnerId, String itemName, Long itemPrice) {
        if (partnerId == null) throw new InvalidParamException("Item.partnerId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("Item.itemPrice");

        return new Item(partnerId, itemName, itemPrice);
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {

        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;
    }

    public void changePrepare() {
        this.status = Status.PREPARE;
    }

    public void changeOnSales() {
        this.status = Status.ON_SALES;
    }

    public void changeEndOfSales() {
        this.status = Status.END_OF_SALES;
    }
}
