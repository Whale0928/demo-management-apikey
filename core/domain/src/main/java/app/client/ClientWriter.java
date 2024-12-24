package app.client;

import app.dto.command.ClientRegistrationCommand;
import app.key.KeyGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientWriter {
    private final ClientRepository clientRepository;
    private final KeyGenerator keyGenerator;

    @Transactional
    public Long create(ClientRegistrationCommand command) throws NoSuchAlgorithmException {

        if (clientRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("이미 등록된 이메일 주소입니다: " + command.email());
        }

        final String key = keyGenerator.generateApiKey();

        Client client = Client.builder()
                .name(command.name())
                .email(command.email())
                .apiKey(key)
                .issuerInfo(command.issuerInfo())
                .permissions(new PermissionsType[]{PermissionsType.READ})
                //     .allowedIps(command.allowedIps())
                .build();

        return clientRepository.save(client).getId();
    }

    @Transactional
    public Long randomCreate() throws NoSuchAlgorithmException {
        final String name = UUID.randomUUID().toString();
        final String email = name + "@demo-app.com";
        final String key = keyGenerator.generateApiKey();

        Client client = Client.builder()
                .name(name)
                .email(email)
                .apiKey(key)
                .issuerInfo("테스트 목적의 클라이언트")
                .permissions(new PermissionsType[]{PermissionsType.READ})
                //       .allowedIps(null)
                .build();

        return clientRepository.save(client).getId();
    }
}
