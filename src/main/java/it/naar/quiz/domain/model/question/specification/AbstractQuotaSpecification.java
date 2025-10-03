package it.naar.quiz.domain.model.question.specification;

import it.naar.quiz.domain.model.question.Percentage;
import it.naar.quiz.domain.model.question.Quota;
import java.util.List;

/**
 * Classe base per specification con stato.
 */
public abstract class AbstractQuotaSpecification implements QuotaSpecification {
    
    protected final Percentage clamp;
    protected boolean satisfied;
    protected String violationMessage;
    
    protected AbstractQuotaSpecification(Percentage clamp) {
        this.clamp = clamp;
        this.satisfied = true;
        this.violationMessage = null;
    }
    
    @Override
    public final QuotaSpecification isSatisfiedBy(List<Quota> quotas) {
        satisfied = checkCondition(quotas);
        return this;
    }
    
    /**
     * Implementato dalle sottoclassi per verificare la condizione specifica.
     * Se non soddisfatta, deve settare violationMessage.
     */
    protected abstract boolean checkCondition(List<Quota> quotas);
    
    @Override
    public boolean getResult() {
        return satisfied;
    }
    
    @Override
    public String getViolationMessage() {
        return violationMessage;
    }
}
