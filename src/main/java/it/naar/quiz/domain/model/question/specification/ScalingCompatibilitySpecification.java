package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Percentage;
import it.naar.quiz.domain.model.question.Quota;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Verifica che lo scaling necessario non porti elementi sotto il clamp.
 * 
 * <p>Invariante: min(elementi > clamp) * scalingFactor >= clamp
 * 
 * <p><strong>Precondizioni:</strong>
 * <ul>
 *   <li>Devono esistere elementi > clamp (sumAdjustable > 0)</li>
 *   <li>Se non soddisfatta, lancia {@link SpecificationPreconditionException}</li>
 * </ul>
 * 
 * <p><strong>Uso corretto:</strong>
 * <pre>{@code
 * QuotaSpecification spec = new ClampSpaceAvailableSpecification(clamp)
 *     .and(new AdjustableElementsExistSpecification(clamp))  // ← Prima!
 *     .and(new ScalingCompatibilitySpecification(clamp));    // ← Dopo
 * }</pre>
 */
public class ScalingCompatibilitySpecification extends AbstractQuotaSpecification {
    
    private static final double TOLERANCE = 1e-6;
    
    public ScalingCompatibilitySpecification(Percentage clamp) {
        super(clamp);
    }
    
    @Override
    protected boolean checkCondition(List<Quota> quotas) {
        long countClamped = quotas.stream()
            .filter(q -> q.percentage().value() <= clamp.value())
            .count();
        
        double sumClampedAfter = countClamped * clamp.value();
        double sumAdjustable = quotas.stream()
            .map(Quota::percentage)
            .mapToDouble(Percentage::value)
            .filter(v -> v > clamp.value())
            .sum();
        
        // ✅ Fail-fast con eccezione se precondizione violata
        if (sumAdjustable < TOLERANCE) {
            throw new SpecificationPreconditionException(
                "ScalingCompatibilitySpecification",
                "sumAdjustable > 0",
                String.format(
                    "Non ci sono elementi adjustable (sumAdjustable=%.2e). " +
                    "Impossibile verificare la compatibilità dello scaling. " +
                    "Usare questa specification dopo AdjustableElementsExistSpecification " +
                    "nella composizione .and()",
                    sumAdjustable
                )
            );
        }

        double availableSpace = 100.0 - sumClampedAfter;
        double scalingFactor = availableSpace / sumAdjustable;

        OptionalDouble minAdjustable = quotas.stream()
            .map(Quota::percentage)
            .mapToDouble(Percentage::value)
            .filter(v -> v > clamp.value())
            .min();
        
        if (minAdjustable.isPresent()) {
            double minValue = minAdjustable.getAsDouble();
            double scaledMin = minValue * scalingFactor;
            
            if (scaledMin < clamp.value() - TOLERANCE) {
                double currentSum = quotas.stream()
                    .map(Quota::percentage)
                    .mapToDouble(Percentage::value)
                    .sum();
                
                violationMessage = String.format(
                    "Invariante [Scaling Compatibility] violato: lo scaling (fattore=%.4f) " +
                    "porterebbe l'elemento minimo (%.2f%%) a %.2f%%, sotto il clamp (%.2f%%). " +
                    "La somma corrente (%.2f%%) è troppo alta per essere normalizzata.",
                    scalingFactor, minValue, scaledMin, clamp.value(), currentSum
                );
                return false;
            }
        }
        
        return true;
    }
}
