package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Quota;
import java.util.List;

/**
 * Specification per validare regole di business sulle Quote.
 * Supporta composizione fluent con operatori logici.
 * 
 * <p>Esempio d'uso:
 * <pre>{@code
 * QuotaSpecification spec = new ClampSpaceAvailableSpecification(clamp)
 *     .and(new AdjustableElementsExistSpecification(clamp))
 *     .and(new ScalingCompatibilitySpecification(clamp));
 * 
 * if (!spec.isSatisfiedBy(quotas).getResult()) {
 *     throw new Exception(spec.getViolationMessage());
 * }
 * }</pre>
 */
public interface QuotaSpecification {
    
    /**
     * Valuta la specifica sulla lista di quote.
     * Restituisce this per consentire chiamate fluent.
     * 
     * @param quotas lista di quote da validare
     * @return this per method chaining
     */
    QuotaSpecification isSatisfiedBy(List<Quota> quotas);
    
    /**
     * Restituisce il risultato dell'ultima valutazione.
     * 
     * @return true se tutte le specifiche valutate sono soddisfatte
     */
    boolean getResult();
    
    /**
     * Restituisce un messaggio descrittivo della prima violazione.
     * 
     * @return messaggio d'errore, o null se nessuna violazione
     */
    String getViolationMessage();
    
    /**
     * Combina questa specifica con un'altra in AND logico.
     * 
     * @param other altra specifica
     * @return nuova specifica composta
     */
    default QuotaSpecification and(QuotaSpecification other) {
        return new AndSpecification(this, other);
    }
    
    /**
     * Combina questa specifica con un'altra in OR logico.
     * 
     * @param other altra specifica
     * @return nuova specifica composta
     */
    default QuotaSpecification or(QuotaSpecification other) {
        return new OrSpecification(this, other);
    }
}
