package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;

public class MenuValutazione {

    private int scelta;

    public MenuValutazione(String userID, Libro l) {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();

            if (ValutazioniHashMap.getInstance().hasValutazione(userID,l.getId().toString())) {

                MenuValutazioneMessaggi.menuValutazionePresente();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

                if (scelta == 1) {
                    var visualizzaValutazione = new VisualizzaValutazione(userID,l);
                    controllo = false;
                }

                if(scelta>1) {
                    scelta++;
                }

            } else {

                MenuValutazioneMessaggi.menuValutazioneAssente();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

                if (scelta == 1) {
                    var inserimentoValutazione = InserimentoValutazione.in(userID, l);
                    controllo = false;
                }

                if (scelta >= 2 && scelta <= 4) {
                    scelta++;
                }
            }

        } while (!controllo);

    }

    public int getScelta() {
        return scelta;
    }
}
