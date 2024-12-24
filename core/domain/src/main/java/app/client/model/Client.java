package app.client.model;


import app.dto.response.ClientMetaResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@ToString
@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Builder.Default
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "api_key", nullable = false, unique = true, length = 512)
    private String apiKey;

    @Column(name = "issuer_info", nullable = false)
    private String issuerInfo;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientPermission> permissions = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientAllowedIp> allowedIps = new HashSet<>();

    @Builder.Default
    @Comment("발급일시")
    @Column(name = "issued_at")
    private LocalDateTime issuedAt = LocalDateTime.now();

    @Builder.Default
    @Comment("요청 횟수")
    @Column(name = "request_count")
    private Long requestCount = 0L;

    public ClientMetaResponse extractMetaInfo() {
        return ClientMetaResponse.builder()
                .id(id)
                .email(email)
                .apiKey(apiKey)
                .issuerInfo(issuerInfo)
                .permissions(permissions.stream().map(p -> p.getId().getPermission().name()).toList())
                .allowedIps(allowedIps.stream().map(i -> i.getId().getIpAddress()).toList())
                .issuedAt(issuedAt.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }

    public void addPermission(ClientPermission... permission) {
        this.permissions.addAll(Set.of(permission));
    }

    public void addAllowedIp(ClientAllowedIp... allowedIp) {
        this.allowedIps.addAll(Set.of(allowedIp));
    }
}
