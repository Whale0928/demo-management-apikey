package app.converter;


import app.type.IpAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IpAddressConverter implements AttributeConverter<IpAddress, String> {
    @Override
    public String convertToDatabaseColumn(IpAddress attribute) {
        return attribute != null ? attribute.value() : null;
    }

    @Override
    public IpAddress convertToEntityAttribute(String dbData) {
        return dbData != null ? new IpAddress(dbData) : null;
    }
}
