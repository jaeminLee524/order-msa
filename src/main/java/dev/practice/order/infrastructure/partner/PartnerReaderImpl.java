package dev.practice.order.infrastructure.partner;

import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PartnerReaderImpl implements PartnerReader {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(String partnerToken) {

        return partnerRepository.findByPartnerToken(partnerToken).orElseThrow(
                () -> new RuntimeException("잘못된 토큰 값입니다.")
        );
    }
}
