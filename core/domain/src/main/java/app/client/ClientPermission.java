package app.client;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "client_permissions")
public record ClientPermission(
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
            String permission
    ) implements Serializable {
    }
}
