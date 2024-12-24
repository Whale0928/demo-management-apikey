package app.client.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@Table(name = "client_allowed_ips")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class ClientAllowedIp {
    @EmbeddedId
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    public static ClientAllowedIp of(Client client, String ipAddress) {
        return new ClientAllowedIp(new Id(client.getId(), ipAddress), client);
    }


    @Getter
    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    @AllArgsConstructor
    public static class Id implements Serializable {
        private UUID clientId;
        private String ipAddress;
    }
}
