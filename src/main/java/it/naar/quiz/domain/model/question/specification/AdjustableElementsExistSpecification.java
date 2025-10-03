package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Percentage;
import it.naar.quiz.domain.model.question.Quota;
import java.util.List;

/**
 * Verifica che esistano elementi > clamp da scalare.
 * 
 * <p>Invariante: sum(elementi > clamp) > 0
 */
public class AdjustableElementsExistSpecification extends AbstractQuotaSpecification {
    
    private static final double TOLERANCE = 1e-9;
    
    public AdjustableElementsExistSpecification(Percentage clamp) {
        super(clamp);
    }
    
    @Override
    protected boolean checkCondition(List<Quota> quotas) {
        double sumAdjustable = quotas.stream()
            .map(Quota::percentage)
            .mapToDouble(Percentage::value)
            .filter(v -> v > clamp.value())
            .sum();
        
        if (sumAdjustable < TOLERANCE) {
            long countClamped = quotas.stream()
                .filter(q -> q.percentage().value() <= clamp.value())
                .count();
            
            double residuo = 100.0 - (countClamped * clamp.value());
            
            violationMessage = String.format(
                "Invariante [Adjustable Elements] violato: nessun elemento > clamp (%.1f%%). " +
                "Impossibile distribuire il residuo di %.2f%%.",
                clamp.value(), residuo
            );
            return false;
        }
        
        return true;
    }
}
