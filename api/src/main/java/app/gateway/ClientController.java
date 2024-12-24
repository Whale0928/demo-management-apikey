package app.gateway;

import app.client.reader.ClientReader;
import app.client.writer.ClientWriter;
import app.dto.request.ClientRegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientReader clientReader;
    private final ClientWriter clientWriter;

    @GetMapping
    public ResponseEntity<?> helloClient() {
        return ResponseEntity.ok(clientReader.helloClient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientInfo(
            @PathVariable String id
    ) {
        log.info("Fetching client info for id: {}", id);
        return ResponseEntity.ok(clientReader.getClientInfo(UUID.fromString(id)));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRegistrationRequest request) throws NoSuchAlgorithmException {
        var command = request.toCommand();
        return ResponseEntity.ok(clientWriter.create(command));
    }
}
