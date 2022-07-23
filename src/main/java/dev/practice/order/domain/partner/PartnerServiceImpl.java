package dev.practice.order.domain.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PartnerServiceImpl implements PartnerService{

    private final PartnerStore partnerStore;
    private final PartnerReader partnerReader;

    @Override
    public PartnerInfo registerPartner(PartnerCommand command) {
        // 1. command -> initPartner => command toEntity()통해 내부에서
        // 2. initPartner -> DB save
        // 3. Partner -> PartnerInfo && return
        Partner initPartner = command.toEntity();
        Partner partner = partnerStore.store(initPartner);

        return PartnerInfo.of(partner);
    }

    @Transactional(readOnly = true)
    @Override
    public PartnerInfo getPartnerInfo(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        return PartnerInfo.of(partner);
    }

    @Override
    public PartnerInfo enablePartner(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.enable();
        return PartnerInfo.of(partner);
    }

    @Override
    public PartnerInfo disablePartner(String partnerToken) {
        Partner partner = partnerReader. getPartner(partnerToken);
        partner.disable();
        return PartnerInfo.of(partner);
    }
}
