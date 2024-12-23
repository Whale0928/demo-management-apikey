package app.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ExcludePath {
    CLIENTS("clients"),
    RESOURCE("resource");
    private final String path;

    public static List<String> getPaths() {
        return Stream.of(ExcludePath.values())
                .map(ExcludePath::getPath)
                .toList();
    }

    public static boolean isExcluded(String path) {
        // 정확한 경로 매칭을 위해 시작 부분 체크
        return getPaths().stream()
                .anyMatch(excludePath -> path.startsWith("/" + excludePath));
    }
}
