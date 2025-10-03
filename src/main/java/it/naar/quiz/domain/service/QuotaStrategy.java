package it.naar.quiz.domain.service;

import it.naar.quiz.domain.model.question.AbstractArgument;
import it.naar.quiz.domain.model.question.Quota;

import java.util.List;
import java.util.Map;

public interface QuotaStrategy {
    static Map<AbstractArgument, Integer> targets(int num, List<Quota> quote) {
        return null;
    }
}
