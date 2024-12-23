package app.security;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Slf4j
@EqualsAndHashCode(callSuper = false)
public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    private final String apiKey;
    private final Principal details;

    public ApiKeyAuthentication(
            String apiKey,
            Collection<? extends GrantedAuthority> authorities,
            Principal details
    ) {
        super(authorities);
        this.apiKey = apiKey;
        this.details = details;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        log.info("getCredentials() called");
        return apiKey;
    }

    @Override
    public Object getPrincipal() {
        return details;
    }

    @Builder
    public record Principal(
            String email,
            String issuerInfo,
            List<String> permissions,
            List<String> allowedIps,
            String issuedAt
    ) implements Serializable {
    }
}
