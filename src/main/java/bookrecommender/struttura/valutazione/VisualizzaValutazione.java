package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.VisualizzaValutazioneMessaggi;

import java.util.Scanner;

/**
 * Classe che ha la funzione di gestire la
 * sezione di visualizzazione della valutazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */


public class VisualizzaValutazione {


    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo VisualizzaValutazione
     * e all'interno viene effettuata la stampa
     * delle valutazioni, dati gli id dell'utente e del
     * libro corrente.
     *
     * @param userId indica l'utente loggato
     *
     * @param libro indica il libro corrente
     */

    public VisualizzaValutazione(String userId, Libro libro) {

        NuovaSchermata.nuovaSchermata();

        String[] valutazioni = ValutazioniHashMap.getInstance().getValues(userId,libro.getId().toString());

        VisualizzaValutazioneMessaggi.intestazione();
        VisualizzaValutazioneMessaggi.valutazioni(valutazioni);

    }

}
