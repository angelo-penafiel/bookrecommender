
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.notifiche;

import bookrecommender.elaborazione.entities.Notifica;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.services.NotificaService;
import bookrecommender.interfaccia.notifiche.GestioneNotificheMessaggi;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.util.List;

public class GestioneNotifiche {

    private final int scelta;

    public GestioneNotifiche(Utente utente) {

        var notificaService=new NotificaService();
        List<Notifica> notifiche = notificaService.getAll(utente.getId());

        NuovaSchermata.nuovaSchermata();
        Headers.menuNotifiche(utente.getUsername());

        GestioneNotificheMessaggi.stampaNotifiche(notifiche);

        GestioneNotificheMessaggi.menu();
        scelta = SceltaMenu.sceltaMenu(3);
    }

    public int getScelta() {
        return scelta;
    }

}
