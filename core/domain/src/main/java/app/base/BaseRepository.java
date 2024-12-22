package app.base;

import java.util.Optional;

public interface BaseRepository {
    Optional<Base> findById(Long id);
    Base save(Base base);
}
