package app.deserializer;


import app.exception.InvalidTypeValueException;
import app.type.IpAddress;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class IpAddressDeserializer extends StdDeserializer<IpAddress> {

    public IpAddressDeserializer() {
        super(IpAddress.class);
    }

    @Override
    public IpAddress deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        log.debug("Deserializing IP address: '{}'", value);
        // 여기서 예외가 발생하면 더 명확한 에러 메시지로 변환
        try {
            return new IpAddress(value);
        } catch (InvalidTypeValueException e) {
            log.error("Failed to deserialize IP: {}", value, e);
            throw new JsonMappingException(p, "Invalid IP address: " + value, e);
        }
    }
}
