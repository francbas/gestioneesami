package it.naar.quiz.domain.service.exceptions;

import it.naar.quiz.domain.model.exceptions.DomainException;

/**
 * Lanciata quando la normalizzazione delle quote non è possibile
 * a causa di vincoli matematici o di business.
 */
public class QuotaNormalizationException extends QuotaException {

    private final long elementCount;
    private final double clampValue;
    private final double currentSum;

    public QuotaNormalizationException(String message, long elementCount, double clampValue, double currentSum) {
        super(message, "QUOTA_NORMALIZATION_FAILED");
        this.elementCount = elementCount;
        this.clampValue = clampValue;
        this.currentSum = currentSum;
    }

    public long getElementCount() {
        return elementCount;
    }

    public double getClampValue() {
        return clampValue;
    }

    public double getCurrentSum() {
        return currentSum;
    }

    @Override
    public String toString() {
        return String.format("%s [elementi=%d, clamp=%.1f%%, somma=%.2f%%]", getMessage(), elementCount, clampValue, currentSum);
    }
}
