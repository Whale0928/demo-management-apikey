package app.client.writer;

import app.client.model.Client;
import app.client.model.ClientAllowedIp;
import app.client.model.ClientPermission;
import app.client.model.ClientRepository;
import app.client.model.PermissionsType;
import app.dto.command.ClientRegistrationCommand;
import app.key.KeyGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientWriter {
    private final ClientRepository clientRepository;
    private final KeyGenerator keyGenerator;

    @Transactional
    public UUID create(ClientRegistrationCommand command) throws NoSuchAlgorithmException {
        final UUID id = UUID.randomUUID();

        if (Boolean.TRUE.equals(clientRepository.existsByEmail(command.email()))) {
            throw new IllegalArgumentException("이미 등록된 이메일 주소입니다: " + command.email());
        }

        Client client = clientRepository.save(Client.builder()
                .id(id)
                .name(command.name())
                .email(command.email())
                .apiKey(keyGenerator.generateApiKey())
                .issuerInfo("테스트 목적의 클라이언트")
                .build());

        List<ClientAllowedIp> list = command.allowedIps().stream().map(ip -> ClientAllowedIp.of(client, ip)).toList();
        ClientPermission clientPermission = ClientPermission.of(client, PermissionsType.READ);

        client.getAllowedIps().addAll(list);
        client.addPermission(clientPermission);

        return clientRepository.save(client).getId();
    }

}
