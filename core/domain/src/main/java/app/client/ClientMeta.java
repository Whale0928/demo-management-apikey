package app.client;

import lombok.Builder;

import java.util.List;

@Builder
public record ClientMeta(
        String email,
        String issuerInfo,
        List<String> permissions,
        List<String> allowedIps,
        String issuedAt
) {
}
