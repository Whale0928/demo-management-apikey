package app.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientReader {
    private final ClientRepository clientRepository;

    public String helloClient() {
        return "Hello Client!" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Optional<ClientMeta> readByApiKey(String apiKey) {
        Optional<Client> client = clientRepository.findByApiKey(apiKey);
        return client.isEmpty() ? Optional.empty() : client.get().extractMetaInfo();
    }
}
