package app.client;

import app.APIKeyGenerator;
import app.dto.command.ClientRegistrationCommand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientWriter {
    private final ClientRepository clientRepository;
    private final APIKeyGenerator keyGenerator;

    @Transactional
    public Long create(ClientRegistrationCommand command) {

        final String key = keyGenerator.generateApiKey();

        Client client = Client.builder()
                .name(command.name())
                .email(command.email())
                .apiKey(key)
                .issuerInfo(command.issuerInfo())
                .permissions(new PermissionsType[]{PermissionsType.READ})
                .allowedIps(command.allowedIps())
                .build();

        return clientRepository.save(client).getId();
    }
}
