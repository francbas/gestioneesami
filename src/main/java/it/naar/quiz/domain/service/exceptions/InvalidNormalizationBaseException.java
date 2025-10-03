package it.naar.quiz.domain.service.exceptions;

/**
 * Eccezione lanciata quando la normalizzazione fallisce a causa di una base di normalizzazione non valida.
 *
 * Questa eccezione viene utilizzata per indicare che la somma corrente dei valori
 * della base di normalizzazione è pari a zero, rendendo impossibile procedere
 * con il calcolo corretto.
 */
public class InvalidNormalizationBaseException extends QuotaException {

    private final double currentSum;


    public InvalidNormalizationBaseException(String message, double currentSum) {
        super(message, "NORMALIZATION_BASE_0_FAILED");
        this.currentSum = currentSum;
    }

    public double getCurrentSum() {
        return currentSum;
    }

    @Override
    public boolean isRecoverable() {
        return false; // Bug algoritmico, non recuperabile a runtime
    }
}
