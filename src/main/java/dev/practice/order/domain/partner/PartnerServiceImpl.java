package dev.practice.order.domain.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService{

    private final PartnerStore partnerStore;

    @Override
    public PartnerInfo registerPartner(PartnerCommand command) {
        // 1. command -> initPartner => command toEntity()통해 내부에서
        // 2. initPartner -> DB save
        // 3. Partner -> PartnerInfo && return

//        Partner initPartner = Partner.of(command.getPartnerName(), command.getBusinessNo(), command.getEmail());
        Partner initPartner = command.toEntity();
        Partner partner = partnerStore.store(initPartner);

        return PartnerInfo.of(partner);
    }

    @Override
    public PartnerInfo getPartnerInfo(String partnerToken) {
        return null;
    }

    @Override
    public PartnerInfo enablePartner(String partnerToken) {
        return null;
    }

    @Override
    public PartnerInfo disablePartner(String partnerToken) {
        return null;
    }
}
