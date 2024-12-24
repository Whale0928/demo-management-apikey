package app.gateway;

import app.client.ClientReader;
import app.client.ClientWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
