package app.client.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "client_permissions")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class ClientPermission {
    @EmbeddedId
    private ClientPermissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    public static ClientPermission of(Client client, PermissionsType permission) {
        return new ClientPermission(new ClientPermissionId(client.getId(), permission), client);
    }


    @Getter
    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    @AllArgsConstructor
    public static class ClientPermissionId implements Serializable {
        private UUID clientId;

        @Enumerated(EnumType.STRING)
        private PermissionsType permission;
    }
}
