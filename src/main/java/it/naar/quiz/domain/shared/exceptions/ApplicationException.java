package it.naar.quiz.domain.shared.exceptions;

public abstract class ApplicationException extends RuntimeException {

    private final String errorCode;

    protected ApplicationException(String message) {
        super(message);
        this.errorCode = null;
    }

    protected ApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
    }

    protected ApplicationException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public abstract boolean isRecoverable();

}
