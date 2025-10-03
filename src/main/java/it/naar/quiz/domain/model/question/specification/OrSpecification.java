package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Quota;
import java.util.List;

/**
 * Combina due specification in OR logico con short-circuit.
 */
class OrSpecification implements QuotaSpecification {
    
    private final QuotaSpecification left;
    private final QuotaSpecification right;
    private boolean result;
    private String violationMessage;
    
    OrSpecification(QuotaSpecification left, QuotaSpecification right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public QuotaSpecification isSatisfiedBy(List<Quota> quotas) {
        left.isSatisfiedBy(quotas);
        
        if (left.getResult()) {
            result = true;
            violationMessage = null;
            return this;
        }
        
        right.isSatisfiedBy(quotas);
        result = right.getResult();
        
        if (!result) {
            violationMessage = left.getViolationMessage() + "; " + right.getViolationMessage();
        }
        
        return this;
    }
    
    @Override
    public boolean getResult() {
        return result;
    }
    
    @Override
    public String getViolationMessage() {
        return violationMessage;
    }
}
