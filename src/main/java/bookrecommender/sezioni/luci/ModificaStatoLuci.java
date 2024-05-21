
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.luci;

import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.exceptions.LuceException;
import bookrecommender.elaborazione.services.LuceService;
import bookrecommender.elaborazione.services.UtenteService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.luci.MenuLuciMessaggi;
import bookrecommender.interfaccia.luci.ModificaStatoLuciMessaggi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModificaStatoLuci {

    private Luce luceModificare;

    private final Scanner in=new Scanner(System.in);

    private List<Luce> luci;

    public ModificaStatoLuci(Utente utente, List<Luce> luci) {

        setLuci(luci);

        for(Luce luce:luci) {

            var utenteService=new UtenteService();
            List<Utente> utenti= utenteService.getAllAssegnamentiLuci(luce.getId());

            for(Utente utente2:utenti) {
                luce.addObserver(utente2);
            }
        }

        NuovaSchermata.nuovaSchermata();
        Headers.modificaLuci(utente.getUsername());
        MenuLuciMessaggi.stampaLuci(luci);
        ModificaStatoLuciMessaggi.separatore();
        sceltaLuce();

        NuovaSchermata.nuovaSchermata();
        Headers.modificaLuci(utente.getUsername());
        ModificaStatoLuciMessaggi.stampaLuce(luceModificare);
        modificaAcceso();
    }

    public final void setLuci(List<Luce> luci) {
        this.luci = new ArrayList<>(luci);
    }

    private void sceltaLuce() {

        var scelta = 0;
        String voce;
        boolean controllo;

        controllo = true;

        ModificaStatoLuciMessaggi.inserimentoScelta();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controllo=false;
            }
        }

        if(voce.isEmpty()) {
            controllo=false;
        }

        if(controllo) {
            scelta=Integer.parseInt(voce);
            if(scelta<1||scelta>luci.size()) {
                ModificaStatoLuciMessaggi.erroreScelta(luci.size());
                throw new LuceException(" Errore! La luce deve essere tra 1 e "+luci.size());
            }
        }

        else {
            ModificaStatoLuciMessaggi.erroreStringa();
            throw new LuceException(" Errore! Inserire un numero!");
        }

        luceModificare = luci.get(scelta-1);
    }


    private void modificaAcceso() {

        String stringa;

        ModificaStatoLuciMessaggi.inserimentoAcceso();
        stringa=in.nextLine();

        luceModificare.setAcceso("on".equalsIgnoreCase(stringa));

        luceModificare.inviaNotifica(0);

        var luceService=new LuceService();
        luceService.update(luceModificare);

    }

}
