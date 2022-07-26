package dev.practice.order.interfaces.partner;

import dev.practice.order.domain.partner.PartnerCommand;
import dev.practice.order.domain.partner.PartnerInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PartnerDtoMapper {

    /** request **/
    PartnerCommand of(PartnerDto.RegisterRequest request);

    /** response **/
    PartnerDto.RegisterResponse of(PartnerInfo partnerInfo);
}
