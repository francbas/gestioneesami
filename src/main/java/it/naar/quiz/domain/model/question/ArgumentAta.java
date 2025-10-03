package it.naar.quiz.domain.model.question;

public class ArgumentAta extends AbstractArgument {
    String atacode;
    String title;

    public ArgumentAta(String atacode, String title) {
        this.atacode = atacode;
        this.title = title;
    }

    public ArgumentAta(String testo, String descrizione, String atacode, String title) {
        super(testo, descrizione);
        this.atacode = atacode;
        this.title = title;
    }

    public String getAtacode() {
        return atacode;
    }

    public void setAtacode(String atacode) {
        this.atacode = atacode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ArgumentAta getAtaChapter() {
        return this;

    }

    @Override
    public boolean isAtaChapter() {
        return true;
    }
}
