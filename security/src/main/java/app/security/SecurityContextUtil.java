package app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtil {

    public String getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.toString();
    }
}
