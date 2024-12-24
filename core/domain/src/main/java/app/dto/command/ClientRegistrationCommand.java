package app.dto.command;

import lombok.Builder;

import java.util.List;

@Builder
public record ClientRegistrationCommand(
        String name,
        String email,
        String issuerInfo,
        List<String> allowedIps
) {
}
