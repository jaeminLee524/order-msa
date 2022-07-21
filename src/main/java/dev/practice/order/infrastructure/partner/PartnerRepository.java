package dev.practice.order.infrastructure.partner;

import dev.practice.order.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {



}
