package app.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ClientMetaResponse(
        UUID id,
        String email,
        String issuerInfo,
        List<String> permissions,
        List<String> allowedIps,
        String issuedAt
) {
}
