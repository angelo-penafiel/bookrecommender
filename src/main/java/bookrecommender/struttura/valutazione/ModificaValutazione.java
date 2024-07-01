package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.elaborazione.entities.valutazione.Valutazione;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.ModificaValutazioneMessaggi;

import java.util.Scanner;

public class ModificaValutazione {

    private int scelta;

    public ModificaValutazione(String UserID, Libro libro) {
        ValutazioniHashMap val = ValutazioniHashMap.getInstance();

        if (val.hasValutazione(UserID, libro.getId().toString())) {

            NuovaSchermata.nuovaSchermata();
            ModificaValutazioneMessaggi.menu();
            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(5);

            if (scelta == 1) {

            }

            if (scelta == 2) {

            }

            if (scelta == 3) {

            }

            if (scelta == 4) {

            }

            if (scelta == 5) {

            }

        } else {
            System.out.println("Questo libro non ha ancora una valutazione, premere qualsiasi tasto per uscire");
            new Scanner(System.in).nextLine();
        }
    }


}
