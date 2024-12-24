package app.exception;

public class InvalidTypeValueException extends RuntimeException {
    public InvalidTypeValueException(String type, String value) {
        super(String.format("Invalid %s format: %s", type, value));
    }

    public InvalidTypeValueException(String type, String value, String reason) {
        super(String.format("Invalid %s format: %s (%s)", type, value, reason));
    }
}
