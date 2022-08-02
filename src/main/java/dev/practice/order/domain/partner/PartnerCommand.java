package dev.practice.order.domain.partner;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class PartnerCommand {
    private String partnerName;
    private String businessNo;
    private String email;

    public PartnerCommand(String partnerName, String businessNo, String email) {
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
    }

    public Partner toEntity() {
        return new Partner(partnerName, businessNo, email);
    }
}
