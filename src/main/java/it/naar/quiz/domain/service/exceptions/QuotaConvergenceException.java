package it.naar.quiz.domain.service.exceptions;

/**
 * Lanciata quando l'algoritmo di normalizzazione non converge
 * entro il numero massimo di iterazioni.
 */
public class QuotaConvergenceException extends QuotaException {

    private final int iterations;
    private final double currentSum;
    private final double finalError;

    public QuotaConvergenceException(String message, int iterations, double currentSum,double finalError) {
        super(message, "QUOTA_CONVERGENCE_FAILED");
        this.iterations = iterations;
        this.currentSum = currentSum;
        this.finalError = finalError;
    }

    public int getIterations() {
        return iterations;
    }

    public double getCurrentSum() {
        return currentSum;
    }

    public double getFinalError() {
        return finalError;
    }

    @Override
    public boolean isRecoverable() {
        return false; // Bug algoritmico, non recuperabile a runtime
    }
}
