package it.naar.quiz.domain.question;

public class ArgomentoAta extends AbstractArgomento {
    String codice;
    String titolo;

    public ArgomentoAta(String codice, String titolo) {
        this.codice = codice;
        this.titolo = titolo;
    }

    public ArgomentoAta(String testo, String descrizione, String codice, String titolo) {
        super(testo, descrizione);
        this.codice = codice;
        this.titolo = titolo;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public ArgomentoAta getAtaChapter() {
        return this;

    }

    @Override
    public boolean isAtaChapter() {
        return true;
    }
}
