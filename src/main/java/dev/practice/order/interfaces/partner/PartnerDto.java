package dev.practice.order.interfaces.partner;

import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerCommand;
import dev.practice.order.domain.partner.PartnerInfo;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PartnerDto {

    @Getter @AllArgsConstructor @NoArgsConstructor
    public static class RegisterRequest {

        @NotEmpty(message = "partnerName 는 필수값입니다")
        private String partnerName;
        @NotEmpty(message = "businessNo 는 필수값입니다")
        private String businessNo;
        @Email(message = "email 형식에 맞아야 합니다")
        @NotEmpty(message = "email 는 필수값입니다")
        private String email;
        public PartnerCommand toCommand() {
            return new PartnerCommand(partnerName, businessNo, email);
        }

    }

    @Getter @Setter
    public static class RegisterResponse {
        private String partnerToken;
        private String partnerName;
        private String businessNo;
        private String email;
        private Partner.Status status;

    }
}