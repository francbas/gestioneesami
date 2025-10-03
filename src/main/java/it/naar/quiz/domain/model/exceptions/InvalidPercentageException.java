package it.naar.quiz.domain.model.exceptions;


/**
 * Lanciata quando un valore percentuale non rispetta i vincoli [1.0, 100.0].
 */
public class InvalidPercentageException extends DomainException {

    private final double invalidValue;

    public InvalidPercentageException(double value) {
        super(String.format("Valore percentuale non valido: %.2f. Deve essere tra 1.0 e 100.0", value),
                "INVALID_PERCENTAGE");
        this.invalidValue = value;
    }

    public double getInvalidValue() {
        return invalidValue;
    }
}
