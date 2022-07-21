package dev.practice.order.domain.partner;

public interface PartnerService {
    // req: Command, Criteria // res: Info

    PartnerInfo registerPartner(PartnerCommand partnerCommand);
    PartnerInfo getPartnerInfo(String partnerToken);
    PartnerInfo enablePartner(String partnerToken);
    PartnerInfo disablePartner(String partnerToken);
}
