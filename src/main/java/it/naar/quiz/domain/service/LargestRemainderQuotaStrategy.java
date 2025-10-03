package it.naar.quiz.domain.service;

import it.naar.quiz.domain.model.question.AbstractArgument;
import it.naar.quiz.domain.model.question.Percentage;
import it.naar.quiz.domain.model.question.Quota;
import it.naar.quiz.domain.model.question.specification.AdjustableElementsExistSpecification;
import it.naar.quiz.domain.model.question.specification.ClampSpaceAvailableSpecification;
import it.naar.quiz.domain.model.question.specification.QuotaSpecification;
import it.naar.quiz.domain.model.question.specification.ScalingCompatibilitySpecification;
import it.naar.quiz.domain.service.exceptions.ExcessiveClampedElementException;
import it.naar.quiz.domain.service.exceptions.InvalidNormalizationBaseException;
import it.naar.quiz.domain.service.exceptions.QuotaConvergenceException;
import it.naar.quiz.domain.service.exceptions.QuotaNormalizationException;

import java.util.*;

/**
 * Metodo dei più alti resti (Largest Remainder/Hamilton): distribuisce gli arrotondamenti in modo equo.
 */
public class LargestRemainderQuotaStrategy implements QuotaStrategy {
    private static Percentage clamp = new Percentage(1.0);
    private QuotaSpecification convergenceSpec;

    public LargestRemainderQuotaStrategy(Percentage clamp, double base) {
        LargestRemainderQuotaStrategy.clamp = clamp;

        this.convergenceSpec = new ClampSpaceAvailableSpecification(clamp)
                .and(new AdjustableElementsExistSpecification(clamp))
                .and(new ScalingCompatibilitySpecification(clamp));
    }

    public static Map<AbstractArgument, Integer> targets(int num, List<Quota> quote) {
        Map<AbstractArgument, Integer> res = new LinkedHashMap<>();
        record Part(AbstractArgument c, int base, double remainder) {
        }
        //this.rebasedTargets();
        List<Part> parts = new ArrayList<>();
        int sumBase = 0;
        for (Quota q : quote) {
            double exact = num * (q.percentage().asFraction());
            int base = (int) Math.floor(exact);
            double rem = exact - base;
            sumBase += base;
            res.put(q.argument(), base);
            parts.add(new Part(q.argument(), base, rem));
        }
        int leftover = Math.max(0, num - sumBase);
        parts.sort((a, b) -> Double.compare(b.remainder, a.remainder));
        for (int i = 0; i < leftover && i < parts.size(); i++) {
            AbstractArgument c = parts.get(i).c;
            res.put(c, res.get(c) + 1);
        }
        return res;
    }

    /**
     * Normalizza le quote a 100% applicando un clamp minimo.
     *
     * <p>Strategia:
     * <ul>
     *   <li>Gli elementi sotto il clamp vengono portati al valore minimo</li>
     *   <li>Il residuo viene distribuito proporzionalmente SOLO tra gli elementi > clamp</li>
     *   <li>Se un elemento è molto dominante, assorbirà la maggior parte del residuo</li>
     * </ul>
     *
     * <p>Esempio: clamp=1%, elementi=[0.3%, 0.5%, 0.8%, 0.2%, 5.0%]
     * <br>Risultato: [1%, 1%, 1%, 1%, 96%]
     *
     * @param quotas lista delle quote da normalizzare
     * @return lista normalizzata con somma = 100%
     * @throws IllegalStateException se la normalizzazione è impossibile
     */
    public List<Quota> rebasedTargets(List<Quota> quotas) throws IllegalStateException {
        if (quotas == null || quotas.isEmpty()) return quotas;

        // Early exit: singolo elemento → rappresenta 100% di se stesso
        if (quotas.size() == 1) {
            Quota single = quotas.get(0);
            return List.of(new Quota(single.argument(), new Percentage(100.0)));
        }

        double summedAll = sumAll(quotas);
        if (Math.abs(summedAll - 100) < 1e-9) return quotas;

        // Early detection: tutti gli elementi sono <= clamp
        boolean allClamped = quotas.stream().allMatch(q -> q.percentage().value() <= clamp.value());
        if (allClamped) {
            double equalShare = 100.0 / quotas.size();
            if (equalShare < clamp.value()) {
                //TODO: aggiungere eccezione custom
                throw new ExcessiveClampedElementException(String
                        .format("Impossibile distribuire 100%% su %d elementi con clamp minimo %.1f%%", quotas.size(), clamp.value())
                        , quotas.size());
            }
            return makeEqualShareQuota(quotas);
        }

        // Early detection: troppi elementi da clampare
        if (!convergenceSpec.isSatisfiedBy(quotas).getResult()) {
            throw new QuotaNormalizationException(
                    convergenceSpec.getViolationMessage(),
                    quotas.size(),
                    clamp.value(),
                    sumAll(quotas)
            );
        }

        // sostituito dalla specification chain - RIMUOVERE!
//        long countToClamp = quotas.stream().filter(q -> q.percentage().value() <= clamp.value()).count();
//        double minSumAfterClamping = countToClamp * clamp.value();
//
//        if (minSumAfterClamping >= 100.0) {
//            throw new QuotaNormalizationException(String.format("Impossibile normalizzare: %d elementi da clampare a %.1f%% = %.1f%% >= 100%%", countToClamp, clamp.value(), minSumAfterClamping), countToClamp, clamp.value(), minSumAfterClamping);
//        }
        // -- RIMUOVERE|

        // Copia profonda degli oggetti Quota
        List<Quota> rebased = new ArrayList<>(quotas.size());
        for (Quota q : quotas) {
            rebased.add(new Quota(q.argument(), new Percentage(q.percentage().value())));
        }

        final double tolerance = 1e-4;
        int maxSteps = 3;
        int steps = 0;

        while (steps++ < maxSteps) {
            double currentSum = sumAll(rebased);
            double error = Math.abs(100 - currentSum) / 100;
            if (error < tolerance)
                //TODO: verificare se questa eccezione è coperta dalla convergence spec
                throw new QuotaConvergenceException(String
                        .format("Impossibile convergere: dopo l'iterazione %d " +
                                        "la base di normalizzazione corrente (%.2f%%) non raggiunge 100%% " +
                                        "con errore %.2f%%"
                                , steps, currentSum, error),
                        steps,
                        currentSum,
                        error);

            if (!convergenceSpec.isSatisfiedBy(rebased).getResult()) {
                throw new QuotaConvergenceException(
                        convergenceSpec.getViolationMessage(),
                        steps,
                        currentSum,
                        error);
            }
            // Applica clamp ai valori bassi
            rebased.stream().filter(q -> q.percentage().value() <= clamp.value()).forEach(quota -> quota.setPercentage(clamp));

            // Check di sicurezza: verifica che ci siano ancora valori da aggiustare
            double sumAdjustableVal = sumAdjustable(rebased);
            if (sumAdjustableVal < 1e-9) {
                // Tutti gli elementi sono ora <= clamp dopo il clamping
                // e sumAdjustable(rebased)==0 -> dividere per 0 --> errore!
                // Questo non dovrebbe mai accadere se early detection funziona,
                // ma gestiamo comunque il caso per robustezza
                throw new InvalidNormalizationBaseException(String
                        .format("Impossibile convergere: dopo l'iterazione %d tutti gli elementi sono <= clamp " +
                                        "ma la somma corrente (%.2f%%) non raggiunge 100%%"
                                , steps
                                , currentSum)
                        , currentSum);
            }

            double finalFactor = (100 - sumClamped(rebased)) / sumAdjustableVal;

            // Scala i valori > clamp
            rebased.stream().filter(q -> q.percentage().value() > clamp.value()).forEach(quota -> quota.setPercentage(new Percentage(quota.percentage().value() * finalFactor)));
        }

        return rebased;
    }

    private static List<Quota> makeEqualShareQuota(List<Quota> quotas) {
        int n = quotas.size();
        List<Quota> res = new ArrayList<>(n);

        for (Quota q : quotas) {
            res.add(new Quota(q.argument(), new Percentage(100.0 / n)));
        }
        return res;
    }

    private static double sumAll(List<Quota> list) {
        return list.stream().map(Quota::percentage).mapToDouble(Percentage::value).sum();
    }

    private static double sumClamped(List<Quota> list) {
        return list.stream().map(Quota::percentage).mapToDouble(Percentage::value).filter(v -> v <= clamp.value()).count() * clamp.value();
    }

    private static double sumAdjustable(List<Quota> list) {
        return list.stream().map(Quota::percentage).mapToDouble(Percentage::value).filter(v -> v > clamp.value()).sum();
    }

}

