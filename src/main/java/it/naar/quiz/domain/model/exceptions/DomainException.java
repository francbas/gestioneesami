package it.naar.quiz.domain.model.exceptions;

import it.naar.quiz.domain.shared.exceptions.ApplicationException;

/**
 * Eccezione di base per tutti gli errori relativi al dominio dell'applicazione.
 * Questa classe estende ApplicationException e viene utilizzata per gestire
 * specificamente le eccezioni che si verificano nel layer del dominio.
 */

public class DomainException extends ApplicationException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DomainException(String message,Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    @Override
    public boolean isRecoverable() {
        return true;
    }

    @Override
    public String getMessage() {
        return String.format("Errore di dominio: %s - codice errore: %s", super.getMessage(), super.getErrorCode());
    }
}
