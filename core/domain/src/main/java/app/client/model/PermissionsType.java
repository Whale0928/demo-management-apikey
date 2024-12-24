package app.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum PermissionsType {
    READ("읽기"),
    WRITE("쓰기"),
    DELETE("삭제");

    private final String description;

    public static Set<PermissionsType> defaultPermissions() {
        return Set.of(READ);
    }

    public static Set<PermissionsType> allPermissions() {
        return Set.of(READ, WRITE, DELETE);
    }

    public static Set<PermissionsType> readOnlyPermissions() {
        return Set.of(READ);
    }

    public static Set<PermissionsType> permissionsByTokenType(String type) {
        return switch (type) {
            case "FULL" -> allPermissions();
            case "LIMITED", "TEMPORARY" -> readOnlyPermissions();
            default -> defaultPermissions();
        };
    }
}
