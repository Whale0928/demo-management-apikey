package app.client.reader;

import app.client.model.Client;
import app.client.model.ClientRepository;
import app.dto.response.ClientMetaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientReader {
    private final ClientRepository clientRepository;

    public String helloClient() {
        return "Hello Client!" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Optional<ClientMetaResponse> readByApiKey(String apiKey) {
        Client client = clientRepository.findByApiKey(apiKey).orElseThrow();
        return Optional.of(client.extractMetaInfo());
    }

    public ClientMetaResponse getClientInfo(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Client with id " + id + " not found"));
        return client.extractMetaInfo();
    }
}
