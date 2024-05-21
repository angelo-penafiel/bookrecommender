
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.luci;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.LuceException;
import bookrecommender.elaborazione.services.LuceService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.luci.EliminazioneLuciMessaggi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EliminazioneLuci {

    private Integer luceEliminareId;

    private final Scanner in=new Scanner(System.in);

    private List<Luce> luci;

    public EliminazioneLuci(Utente utente, List<Luce> luci) {

        setLuci(luci);

        NuovaSchermata.nuovaSchermata();
        Headers.eliminaLuci(utente.getUsername());
        EliminazioneLuciMessaggi.stampaLuci(luci);
        EliminazioneLuciMessaggi.separatore();
        sceltaLuce();

        var luceService=new LuceService();
        luceService.removeById(luceEliminareId,utente.getId());
    }

    public final void setLuci(List<Luce> luci) {
        this.luci = new ArrayList<>(luci);
    }

    private void sceltaLuce() {

        var scelta = 0;
        String voce;
        boolean controllo;

        controllo = true;

        EliminazioneLuciMessaggi.inserimentoScelta();
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
                EliminazioneLuciMessaggi.erroreScelta(luci.size());
                throw new LuceException(" Errore! La luce deve essere tra 1 e "+luci.size());
            }
        }

        else {
            EliminazioneLuciMessaggi.erroreStringa();
            throw new LuceException(" Errore! Inserire un numero!");
        }

        luceEliminareId=luci.get(scelta-1).getId();
    }
}
