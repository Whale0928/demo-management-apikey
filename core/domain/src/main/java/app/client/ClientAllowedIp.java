package app.client;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "client_allowed_ips")
public record ClientAllowedIp(
        @EmbeddedId
        Id id,

        @ManyToOne
        @MapsId("clientId")
        @JoinColumn(name = "client_id")
        Client client
) {
    @Embeddable
    public record Id(
            Long clientId,
            @Column(name = "ip_address")
            String ipAddress
    ) implements Serializable {
    }
}
