package app.security;

import app.client.ClientMeta;
import app.client.ClientReader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String ROLE_PREFIX = "ROLE_";
    private final ClientReader clientReader;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if (Objects.isNull(apiKey)) {
            throw new BadCredentialsException("api key required");
        }

        ClientMeta meta = clientReader.readByApiKey(apiKey)
                .orElseThrow(() -> new BadCredentialsException("not fount client key:" + apiKey));

        ApiKeyAuthentication.Principal principal = ApiKeyAuthentication.Principal.builder()
                .email(meta.email())
                .issuerInfo(meta.issuerInfo())
                .permissions(meta.permissions())
                .allowedIps(meta.allowedIps())
                .issuedAt(meta.issuedAt())
                .build();

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(meta.permissions().stream()
                .map(permission -> ROLE_PREFIX + permission).toList());

        return new ApiKeyAuthentication(apiKey, authorityList, principal);
    }
}
