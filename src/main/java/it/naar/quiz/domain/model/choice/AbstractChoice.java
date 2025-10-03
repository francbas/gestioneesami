package it.naar.quiz.domain.model.choice;

import it.naar.quiz.domain.shared.BaseEntitySupportUUID;
import it.naar.quiz.domain.model.question.AbstractQuestion;

//@Entity
//@Table(name = "quuestion_choice", uniqueConstraints = @jakarta.persistence.UniqueConstraint(columnNames = "public_uuid"))
//@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
public abstract class AbstractChoice extends BaseEntitySupportUUID {
//    @NotBlank
    private String text;

    AbstractQuestion question;

    public AbstractChoice() {
    }

    public AbstractChoice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AbstractQuestion getQuestion() {
        return question;
    }

    public void setQuestion(AbstractQuestion question) {
        this.question = question;
    }
}