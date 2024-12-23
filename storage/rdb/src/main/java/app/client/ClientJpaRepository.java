package app.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends ClientRepository, JpaRepository<Client, Integer> {

    @Override
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Optional<Client> findByEmail(String email);

    @Override
    @Query("SELECT c FROM Client c WHERE c.apiKey =:apiKey")
    Optional<Client> findByApiKey(String apiKey);
}
