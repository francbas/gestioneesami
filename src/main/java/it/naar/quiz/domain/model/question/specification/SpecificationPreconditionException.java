package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.service.exceptions.QuotaException;

/**
 * Lanciata quando una specification non può essere valutata
 * perché una precondizione necessaria non è soddisfatta.
 * 
 * <p>Esempio: ScalingCompatibilitySpecification richiede che esistano
 * elementi adjustable. Se non ci sono, lancia questa eccezione invece
 * di restituire true/false.
 */
public class SpecificationPreconditionException extends QuotaException {
    
    private final String specificationName;
    private final String precondition;
    
    public SpecificationPreconditionException(
            String specificationName,
            String precondition,
            String message) {
        super(
            String.format("[%s] Precondizione non soddisfatta: %s. %s",
                specificationName, precondition, message),
            "SPECIFICATION_PRECONDITION_FAILED"
        );
        this.specificationName = specificationName;
        this.precondition = precondition;
    }
    
    public String getSpecificationName() {
        return specificationName;
    }
    
    public String getPrecondition() {
        return precondition;
    }
    
    @Override
    public boolean isRecoverable() {
        return false; // Errore di composizione, non recuperabile
    }
}
