package app.client;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByApiKey(String apiKey);

    Boolean existsByEmail(String email);

    Boolean existsByApiKey(String apiKey);

    Client save(Client client);

    Optional<Client> findById(Integer id);
}
