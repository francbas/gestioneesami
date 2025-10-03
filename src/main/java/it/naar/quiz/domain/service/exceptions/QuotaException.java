package it.naar.quiz.domain.service.exceptions;

import it.naar.quiz.domain.model.exceptions.DomainException;

/**
 * Eccezione specifica per gestire errori relativi alle quote nel dominio dell'applicazione.
 * Questa eccezione viene lanciata quando si verificano problemi nella gestione o nella
 * validazione delle quote.
 */
public class QuotaException extends DomainException {

    /**
     * Costruisce una nuova QuotaException con il messaggio specificato.
     *
     * @param message il messaggio di errore
     */
    public QuotaException(String message) {
        super(message);
    }

    /**
     * Costruisce una nuova QuotaException con il messaggio e il codice di errore specificati.
     *
     * @param message   il messaggio di errore
     * @param errorCode il codice di errore
     */
    public QuotaException(String message, String errorCode) {
        super(message, errorCode);
    }

    /**
     * Costruisce una nuova QuotaException con il messaggio e la causa specificati.
     *
     * @param message il messaggio di errore
     * @param cause   la causa dell'eccezione
     */
    public QuotaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruisce una nuova QuotaException con il messaggio, la causa e il codice di errore specificati.
     *
     * @param message   il messaggio di errore
     * @param cause     la causa dell'eccezione
     * @param errorCode il codice di errore
     */
    public QuotaException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

}
