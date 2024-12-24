package app.gateway;

import app.aop.CountApiCall;
import app.security.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {

    private final SecurityContextUtil securityContextUtil;

    @CountApiCall
    @GetMapping("/A")
    public ResponseEntity<?> selectAuthenticationInfoA() {
        log.info("AAA");
        String authentication = securityContextUtil.getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/B")
    public ResponseEntity<?> selectAuthenticationInfoB() {
        log.info("BBB");
        String authentication = securityContextUtil.getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
