package it.naar.quiz.domain.question;

public abstract class AbstractArgomento {
    private int id;
    private String testo;
    private String descrizione;

    public AbstractArgomento() {
    }

    public AbstractArgomento(String testo, String descrizione) {
        this.testo = testo;
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTesto() {
        return testo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public abstract ArgomentoAta getAtaChapter();
    public abstract boolean isAtaChapter();
}
