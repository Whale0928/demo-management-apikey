package app;

import app.base.Base;
import app.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final BaseService baseService;

    @GetMapping("/api")
    public Base api() {
        return baseService.next();
    }
}
