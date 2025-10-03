package it.naar.quiz.domain.model.question;

public abstract class AbstractArgument {
    private int id;
    private String text;
    private String description;

    public AbstractArgument() {
    }

    public AbstractArgument(String text, String description) {
        this.text = text;
        this.description = description;
    }

    public int getMockId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public abstract ArgumentAta getAtaChapter();
    public abstract boolean isAtaChapter();
}
