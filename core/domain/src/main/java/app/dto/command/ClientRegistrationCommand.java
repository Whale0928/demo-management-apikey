package app.dto.command;

import app.type.IpAddress;
import lombok.Builder;

@Builder
public record ClientRegistrationCommand(
        String name,
        String email,
        String issuerInfo,
                IpAddress[] allowedIps

) {
}
