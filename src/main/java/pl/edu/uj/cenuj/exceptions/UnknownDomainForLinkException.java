package pl.edu.uj.cenuj.exceptions;

public class UnknownDomainForLinkException extends Exception {
    private static final String INVALID_DAY_MESSAGE = "Day should be formatted as yyyy-mm-dd";

    public static UnknownDomainForLinkException withInvalidDayMessage() {
        return new UnknownDomainForLinkException(INVALID_DAY_MESSAGE);
    }
    public UnknownDomainForLinkException(String message) {
        super(message);
    }
}
