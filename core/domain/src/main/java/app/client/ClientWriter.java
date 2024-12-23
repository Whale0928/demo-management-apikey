package app.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientWriter {
    private final ClientRepository clientRepository;

    public Integer saveClient(Client client) {
        return null;
    }
}
