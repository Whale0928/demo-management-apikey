package app.client;


import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_id_seq")
    @SequenceGenerator(name = "clients_id_seq", sequenceName = "clients_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "api_key", nullable = false, unique = true, length = 512)
    private String apiKey;

    @Column(name = "issuer_info", nullable = false)
    private String issuerInfo;

    @Comment("권한")
    @Type(StringArrayType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "permissions", nullable = false, columnDefinition = "_text")
    private PermissionsType[] permissions;

    @Comment("허용 IP")
    @Type(StringArrayType.class)
    @Column(name = "allowed_ips", nullable = false, columnDefinition = "_text")
    private String[] allowedIps;

    @Builder.Default
    @Comment("발급일시")
    @Column(name = "issued_at")
    private LocalDateTime issuedAt = LocalDateTime.now();

    @Builder.Default
    @Comment("요청 횟수")
    @Column(name = "request_count")
    private Long requestCount = 0L;

    public void incrementRequestCount(int count) {
        this.requestCount += count;
    }

    public Optional<ClientMeta> extractMetaInfo() {
        return Optional.of(ClientMeta.builder()
                .email(email)
                .issuerInfo(issuerInfo)
                .permissions(Arrays.stream(permissions).map(PermissionsType::name).toList())
                .allowedIps(Arrays.asList(allowedIps))
                .issuedAt(issuedAt.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build()
        );
    }
}
