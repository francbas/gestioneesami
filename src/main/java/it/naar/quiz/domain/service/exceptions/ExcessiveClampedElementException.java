package it.naar.quiz.domain.service.exceptions;

/**
 * Eccezione lanciata quando il numero di elementi clampati nel processo di normalizzazione
 * supera la soglia consentita. In generale, questo problema si verifica quando si tenta di normalizzare
 * una quota con un numero di elementi con valore prossimo al clamp elevato.
 * <p>
 * Esempio: Con clamp=1.0% e 150 elementi con valore 1.1%, la somma totale è 165%.
 * Per normalizzare a 100%, ogni elemento dovrebbe essere moltiplicato per 100/165 ≈ 0.606,
 * ma questo porterebbe ogni elemento a 0.67%, violando il vincolo del clamp minimo dell'1%.
 * <p>
 * Questa eccezione indica un errore algoritmico nella gestione del vincolo clamp, non
 * recuperabile a runtime.
 */
public class ExcessiveClampedElementException extends QuotaException {

    /**
     * Dimensione della quota che ha causato l'errore di clamp eccessivo.
     */
    private final double quotaSize;

    public ExcessiveClampedElementException(String message, double quotaSize) {
        super(message, "EXCESSIVE_CLAMPED_ELEMENT_FAILED");
        this.quotaSize = quotaSize;
    }

    public double getQuotaSize() {
        return quotaSize;
    }

    @Override
    public boolean isRecoverable() {
        return false; // Bug algoritmico, non recuperabile a runtime
    }
}
