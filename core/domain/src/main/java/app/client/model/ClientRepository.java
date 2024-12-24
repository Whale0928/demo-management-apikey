package app.client.model;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByApiKey(String apiKey);

    Boolean existsByEmail(String email);

    Boolean existsByApiKey(String apiKey);

    Client save(Client client);

    Optional<Client> findById(UUID id);
}
