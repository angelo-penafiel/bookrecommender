package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu valutazione.
 *
 * @author Angelo Penafiel, Leonardo Basso
 * @version 1.0
 */

public class MenuValutazione {


    //CAMPO

    /**
     * Campo che indica il numero di scelta
     * effettuata del menu valutazione.
     */

    private int scelta;


    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo MenuValutazione
     * e all'interno viene effettuata la stampa
     * del menu e l'inserimento della scelta del
     * menu valutazione. Successivamente viene gestito
     * il flusso del programma in base alla scelta
     * effettuata.
     *
     * @param userId indica lo userId dell'utente
     *               loggato
     *
     * @param libro indica il libro corrente
     */

    public MenuValutazione(String userId, Libro libro) {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();

            if (ValutazioniHashMap.getInstance().hasValutazione(userId,libro.getId().toString())) {

                MenuValutazioneMessaggi.menuValutazionePresente();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

                if (scelta == 1) {
                    var visualizzaValutazione = new VisualizzaValutazione(userId,libro);
                    controllo = false;
                }

            } else {

                MenuValutazioneMessaggi.menuValutazioneAssente();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

                if (scelta == 1) {
                    var inserimentoValutazione = InserimentoValutazione.in(userId, libro);
                    controllo = false;
                }

            }

        } while (!controllo);

    }


    //METODO

    /**
     * Restituisce la scelta effettuata
     * del menu valutazione.
     *
     * @return la scelta
     */

    public int getScelta() {
        return scelta;
    }
}
