package dev.practice.order.domain.order.fragment;

import dev.practice.order.common.exception.InvalidParamException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class DeliveryFragment {

    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    protected DeliveryFragment(String receiverName, String receiverPhone, String receiverZipcode, String receiverAddress1, String receiverAddress2, String etcMessage) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.etcMessage = etcMessage;
    }

    /** 생성자 메서드 **/
    public static DeliveryFragment of(String receiverName, String receiverPhone, String receiverZipcode, String receiverAddress1, String receiverAddress2, String etcMessage) {
        if (StringUtils.isEmpty(receiverName)) throw new InvalidParamException("Delivery.receiverName");
        if (StringUtils.isEmpty(receiverPhone)) throw new InvalidParamException("Delivery.receiverPhone");
        if (StringUtils.isEmpty(receiverZipcode)) throw new InvalidParamException("Delivery.receiverZipcode");
        if (StringUtils.isEmpty(receiverAddress1)) throw new InvalidParamException("Delivery.receiverAddress1");
        if (StringUtils.isEmpty(receiverAddress2)) throw new InvalidParamException("Delivery.receiverAddress2");
        if (StringUtils.isEmpty(etcMessage)) throw new InvalidParamException("Delivery.etcMessage");

        return new DeliveryFragment(receiverName, receiverPhone, receiverZipcode, receiverAddress1, receiverAddress2, etcMessage);
    }
}
