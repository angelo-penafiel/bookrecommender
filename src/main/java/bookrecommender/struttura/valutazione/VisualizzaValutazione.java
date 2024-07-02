package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.VisualizzaValutazioneMessaggi;

import java.util.Scanner;

public class VisualizzaValutazione {

    public VisualizzaValutazione(String UserID, Libro libro) {

        NuovaSchermata.nuovaSchermata();

        String[] valutazioni = ValutazioniHashMap.getInstance().getValues(UserID,libro.getId().toString());

        VisualizzaValutazioneMessaggi.intestazione();
        VisualizzaValutazioneMessaggi.valutazioni(valutazioni);

    }


}
