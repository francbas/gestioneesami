package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Percentage;
import it.naar.quiz.domain.model.question.Quota;
import java.util.List;

/**
 * Verifica che lo spazio occupato dagli elementi clampati sia < 100%.
 * 
 * <p>Invariante: sum(elementi ≤ clamp) * clamp < 100%
 */
public class ClampSpaceAvailableSpecification extends AbstractQuotaSpecification {
    
    private static final double TOLERANCE = 1e-6;
    
    public ClampSpaceAvailableSpecification(Percentage clamp) {
        super(clamp);
    }
    
    @Override
    protected boolean checkCondition(List<Quota> quotas) {
        long countClamped = quotas.stream()
            .filter(q -> q.percentage().value() <= clamp.value())
            .count();
        
        double sumClampedAfter = countClamped * clamp.value();
        
        if (sumClampedAfter >= 100.0 - TOLERANCE) {
            violationMessage = String.format(
                "Invariante [Clamp Space] violato: %d elementi clampati occupano %.2f%% >= 100%%. " +
                "Impossibile allocare spazio per altri elementi.",
                countClamped, sumClampedAfter
            );
            return false;
        }
        
        return true;
    }
}
