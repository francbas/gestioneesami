package it.naar.quiz.domain.model.question;

import java.util.Objects;

public final class Quota {
    private AbstractArgument argument;
    private Percentage percentage;

    public Quota(AbstractArgument argument, Percentage percentage) {
        this.argument = argument;
        this.percentage = percentage;
    }

    public AbstractArgument argument() {
        return argument;
    }

    public Percentage percentage() {
        return percentage;
    }

    public AbstractArgument getArgument() {
        return argument;
    }

    public void setArgument(AbstractArgument argument) {
        this.argument = argument;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public void setPercentage(Percentage percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Quota) obj;
        return Objects.equals(this.argument, that.argument) &&
                Objects.equals(this.percentage, that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(argument, percentage);
    }

    @Override
    public String toString() {
        return "Quota[" +
                "argument=" + argument + ", " +
                "percentage=" + percentage + ']';
    }


}
