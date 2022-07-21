package dev.practice.order.domain.partner;

import lombok.Getter;

@Getter
public class PartnerInfo {
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;
    private Partner.Status status;
}
