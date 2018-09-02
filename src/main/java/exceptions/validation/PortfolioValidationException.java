package exceptions.validation;

public class PortfolioValidationException extends RuntimeException {
    public PortfolioValidationException(String message) {
        super(message);
    }

    public PortfolioValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortfolioValidationException(Throwable cause) {
        super(cause);
    }

    public PortfolioValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
