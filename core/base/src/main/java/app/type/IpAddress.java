package app.type;

import app.deserializer.IpAddressDeserializer;
import app.exception.InvalidTypeValueException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonDeserialize(using = IpAddressDeserializer.class)
public record IpAddress(String value) {
    public IpAddress {
        if (!isValidIpFormat(value)) {
            log.error("Invalid IP format: {}", value);
            throw new InvalidTypeValueException("IP", value);
        }
        log.info("Valid IP address created: {}", value);
    }

    private static boolean isValidIpFormat(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int num = Integer.parseInt(part.trim());
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
