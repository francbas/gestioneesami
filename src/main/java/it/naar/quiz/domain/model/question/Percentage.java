package it.naar.quiz.domain.model.question;// Java

import it.naar.quiz.domain.model.exceptions.InvalidPercentageException;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.util.Objects;

public record Percentage(@DecimalMin("1.0") @DecimalMax("100.0") double value) {
    public Percentage {
        if (Double.isNaN(value) || value < 1.0 || value > 100.0) {
            throw new InvalidPercentageException(value);
        }
    }

    public double asFraction() {
        return value / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Percentage that = (Percentage) o;
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
