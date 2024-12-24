package app.dto.request;

import app.dto.command.ClientRegistrationCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientRegistrationRequest(
        @NotBlank(message = "이름은 필수입니다")
        @Size(min = 1, max = 100, message = "이름은 1-100자 사이여야 합니다")
        String name,

        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이어야 합니다")
        String email,

        @NotBlank(message = "발급자 정보는 필수입니다")
        @Size(min = 1, max = 200, message = "발급자 정보는 1-200자 사이여야 합니다")
        String issuerInfo,

        @NotEmpty(message = "허용 IP는 최소 1개 이상이어야 합니다")
        List<String> allowedIps
) {
    public ClientRegistrationCommand toCommand() {
        return ClientRegistrationCommand.builder()
                .name(name)
                .email(email)
                .issuerInfo(issuerInfo)
                .allowedIps(allowedIps)
                .build();
    }
}
