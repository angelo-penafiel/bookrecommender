
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.LavatriceException;
import bookrecommender.elaborazione.services.LavatriceService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.lavatrici.EliminazioneLavatriciMessaggi;
import bookrecommender.interfaccia.lavatrici.MenuLavatriciMessaggi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EliminazioneLavatrici {

    private Integer lavatriceEliminareId;

    private final Scanner in=new Scanner(System.in);

    private List<Lavatrice> lavatrici;

    public EliminazioneLavatrici(Utente utente, List<Lavatrice> lavatrici) {

        setLavatrici(lavatrici);

        NuovaSchermata.nuovaSchermata();
        Headers.eliminaLavatrici(utente.getUsername());

        MenuLavatriciMessaggi.stampaLavatrici(lavatrici);

        EliminazioneLavatriciMessaggi.separatoreScelta();
        sceltaLavatrice();

        var lavatriceService=new LavatriceService();
        lavatriceService.removeById(lavatriceEliminareId, utente.getId());
    }

    public final void setLavatrici(List<Lavatrice> lavatrici) {
        this.lavatrici=new ArrayList<>(lavatrici);
    }

    private void sceltaLavatrice() {

        var scelta = 0;
        String voce;
        boolean controllo;

        controllo = true;

        EliminazioneLavatriciMessaggi.inserimentoScelta();
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
            if(scelta<1||scelta>lavatrici.size()) {
                EliminazioneLavatriciMessaggi.erroreScelta(lavatrici.size());
                throw new LavatriceException(" Errore! La lavatrice deve essere tra 1 e "+lavatrici.size());

            }
        }

        else {
            EliminazioneLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(" Errore! Inserire un numero!");
        }

        lavatriceEliminareId=lavatrici.get(scelta-1).getId();
    }
}
