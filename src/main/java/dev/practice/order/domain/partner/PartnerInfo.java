package dev.practice.order.domain.partner;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class PartnerInfo {
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;
    private Partner.Status status;

    public PartnerInfo(Long id, String partnerToken, String partnerName, String businessNo, String email, Partner.Status status) {
        this.id = id;
        this.partnerToken = partnerToken;
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = status;
    }

    public static PartnerInfo of(Partner partner) {
        return new PartnerInfo(
                partner.getId(),
                partner.getPartnerToken(),
                partner.getPartnerName(),
                partner.getBusinessNo(),
                partner.getEmail(),
                partner.getStatus());
    }
}
