package app.base;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;

    public Base next() {
        Base base = new Base(1L);
        return baseRepository.save(base);
    }
}
