package app.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionsType {
    READ("읽기"),
    WRITE("쓰기"),
    DELETE("삭제");

    private final String description;

    public static PermissionsType[] defaultPermissions() {
        return new PermissionsType[]{READ};
    }

    public static PermissionsType[] allPermissions() {
        return new PermissionsType[]{READ, WRITE, DELETE};
    }

    public static PermissionsType[] readOnlyPermissions() {
        return new PermissionsType[]{READ};
    }

    public static PermissionsType[] permissionsByTokenType(String type) {
        return switch (type) {
            case "FULL" -> allPermissions();
            case "LIMITED", "TEMPORARY" -> readOnlyPermissions();
            default -> defaultPermissions();
        };
    }
}
