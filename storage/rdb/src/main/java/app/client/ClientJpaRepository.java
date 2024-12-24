package app.client;

import app.client.model.Client;
import app.client.model.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientJpaRepository extends ClientRepository, JpaRepository<Client, UUID> {

    @Override
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> findByEmail(String email);

    @Override
    @Query("SELECT c FROM Client c WHERE c.apiKey =:apiKey")
    Optional<Client> findByApiKey(String apiKey);
}
