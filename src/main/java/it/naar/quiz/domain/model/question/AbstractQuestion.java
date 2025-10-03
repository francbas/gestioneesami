package it.naar.quiz.domain.model.question;

import it.naar.quiz.domain.shared.BaseEntitySupportUUID;
import it.naar.quiz.domain.model.choice.AbstractChoice;

import java.util.List;

public abstract class AbstractQuestion extends BaseEntitySupportUUID {
    private String text;
    private QuestionScope scope;
    private List<AbstractChoice> choices;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionScope getScope() {
        return scope;
    }

    public void setScope(QuestionScope scope) {
        this.scope = scope;
    }

    public List<AbstractChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<AbstractChoice> choices) {
        this.choices = choices;
    }

    public void addChoice(AbstractChoice choice) {
        this.choices.add(choice);
    }

    public void removeChoice(AbstractChoice choice) {
        this.choices.remove(choice);
    }

}
