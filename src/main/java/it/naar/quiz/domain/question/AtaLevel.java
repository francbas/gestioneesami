package it.naar.quiz.domain.question;

/**
 * La classe AtaLevel rappresenta un livello di conoscenza relativo ad un argomento specifico.
 * Include un'enumerazione Livello che definisce tre diversi gradi di competenza.
 */
public class AtaLevel {
    enum Livello {
        L1(1, "Livello 1: Consapevolezza e familiarità di base con l'argomento."),
        L2(2, "Livello 2: Conoscenza generale e comprensione degli aspetti teorici e pratici della materia."),
        L3(3, "Livello 3: Conoscenza dettagliata, compresi i fondamenti teorici, le interrelazioni con altre materie e la capacità di combinare e applicare le conoscenze in modo completo.");

        private final int ordine;
        private final String descrizione;

        Livello(int ordine, String descrizione) {
            this.ordine = ordine;
            this.descrizione = descrizione;
        }

        /**
         * Restituisce l'ordine associato al livello corrente.
         *
         * @return l'ordine del livello come intero.
         */
        public int getOrdine() {
            return ordine;
        }

        /**
         * Restituisce la descrizione associata al livello corrente.
         *
         * @return la descrizione del livello come stringa.
         */
        public String getDescrizione() {
            return descrizione;
        }
    }
}
