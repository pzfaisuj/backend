package pl.edu.uj.cenuj.exceptions;

public class InvalidDateFormatException extends Exception {
    private static final String INVALID_DAY_MESSAGE = "Day should be formatted as yyyy-mm-dd";

    public static InvalidDateFormatException withInvalidDayMessage() {
        return new InvalidDateFormatException(INVALID_DAY_MESSAGE);
    }
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
