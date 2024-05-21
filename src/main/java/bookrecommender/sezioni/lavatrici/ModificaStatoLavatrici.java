
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.LavatriceException;
import bookrecommender.elaborazione.services.LavatriceService;
import bookrecommender.elaborazione.services.UtenteService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.lavatrici.MenuLavatriciMessaggi;
import bookrecommender.interfaccia.lavatrici.ModificaStatoLavatriciMessaggi;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModificaStatoLavatrici {

    private static String erroreStringa = " Errore! Inserire un numero!";

    private int scelta;

    private Lavatrice lavatriceModificare;

    private final Scanner in=new Scanner(System.in);

    private List<Lavatrice> lavatrici;

    public ModificaStatoLavatrici(Utente utente, List<Lavatrice> lavatrici) {

        setLavatrici(lavatrici);

        for(Lavatrice lavatrice:lavatrici) {

            var utenteService=new UtenteService();
            List<Utente> utenti= utenteService.getAllAssegnamentiLavatrici(lavatrice.getId());

            for(Utente utente2:utenti) {
                lavatrice.addObserver(utente2);
            }
        }

        NuovaSchermata.nuovaSchermata();
        Headers.modificaLavatrici(utente.getUsername());
        MenuLavatriciMessaggi.stampaLavatrici(lavatrici);
        ModificaStatoLavatriciMessaggi.separatore();
        sceltaLavatrice();

        boolean controllo;

        do {
            controllo =true;

            NuovaSchermata.nuovaSchermata();
            Headers.modificaLavatrici(utente.getUsername());
            ModificaStatoLavatriciMessaggi.stampaLavatrice(lavatriceModificare);
            ModificaStatoLavatriciMessaggi.menu();
            scelta = SceltaMenu.sceltaMenu(6);

            if(scelta==1) {

                controllo =false;
                NuovaSchermata.nuovaSchermata();
                Headers.modificaLavatrici(utente.getUsername());
                ModificaStatoLavatriciMessaggi.stampaLavatrice(lavatriceModificare);
                ModificaStatoLavatriciMessaggi.separatore();
                modificaAcceso();
            }

            if(scelta==2) {

                controllo =false;
                NuovaSchermata.nuovaSchermata();
                Headers.modificaLavatrici(utente.getUsername());
                ModificaStatoLavatriciMessaggi.stampaLavatrice(lavatriceModificare);
                ModificaStatoLavatriciMessaggi.separatore();
                modificaLavaggio();
            }


        } while(!controllo);

    }

    public final void setLavatrici(List<Lavatrice> lavatrici) {
        this.lavatrici=new ArrayList<>(lavatrici);
    }

    public Integer getScelta() {
        return scelta;
    }

    private void sceltaLavatrice() {

        var sceltaLavatrice = 0;
        String voce;
        boolean controlloSceltaLavatrice;

        controlloSceltaLavatrice = true;

        ModificaStatoLavatriciMessaggi.inserimentoScelta();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controlloSceltaLavatrice=false;
            }
        }

        if(voce.isEmpty()) {
            controlloSceltaLavatrice=false;
        }

        if(controlloSceltaLavatrice) {
            sceltaLavatrice=Integer.parseInt(voce);
            if(sceltaLavatrice<1||sceltaLavatrice> lavatrici.size()) {
                ModificaStatoLavatriciMessaggi.erroreScelta(lavatrici.size());
                throw new LavatriceException(" Errore! La lavatrice deve essere tra 1 e "+lavatrici.size());
            }
        }

        else {
            ModificaStatoLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(erroreStringa);
        }

        lavatriceModificare = lavatrici.get(sceltaLavatrice-1);
    }

    private void modificaAcceso() {

        String stringa;

        ModificaStatoLavatriciMessaggi.inserimentoAcceso();
        stringa=in.nextLine();

        lavatriceModificare.setAcceso("on".equalsIgnoreCase(stringa));

        if(!Boolean.TRUE.equals(lavatriceModificare.getAcceso())) {
            lavatriceModificare.setInizio(null);
            lavatriceModificare.setFine(null);
            lavatriceModificare.setTemperatura(0);
            lavatriceModificare.setTempo(0);
        }

        lavatriceModificare.inviaNotifica(0);

        var lavatriceService=new LavatriceService();
        lavatriceService.update(lavatriceModificare);

    }

    private void modificaLavaggio() {

        String stringa;

        ModificaStatoLavatriciMessaggi.inserimentoStatoLavaggio();
        stringa=in.nextLine();

        if("si".equalsIgnoreCase(stringa)) {

            lavatriceModificare.setInizio(LocalDateTime.now());
            lavatriceModificare.setAcceso(true);

            modificaTemperatura();
            modificaTempo();

            lavatriceModificare.setFine(lavatriceModificare.getInizio().plusMinutes(lavatriceModificare.getTempo()));
        }

        else {

            lavatriceModificare.setInizio(null);
            lavatriceModificare.setFine(null);
            lavatriceModificare.setTemperatura(0);
            lavatriceModificare.setTempo(0);
        }

        lavatriceModificare.inviaNotifica(1);

        var lavatriceService=new LavatriceService();
        lavatriceService.update(lavatriceModificare);

    }

    private void modificaTemperatura() {

        var temperatura = 0;
        String voce;
        boolean controlloTemperatura;

        controlloTemperatura = true;

        ModificaStatoLavatriciMessaggi.inserimentoTemperatura();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controlloTemperatura=false;
            }
        }

        if(controlloTemperatura) {
            temperatura=Integer.parseInt(voce);
            if(temperatura<Lavatrice.MIN_TEMPERATURA||temperatura>Lavatrice.MAX_TEMPERATURA) {
                ModificaStatoLavatriciMessaggi.erroreTemperatura();
                throw new LavatriceException(" Errore! La temperatura deve essere tra "+Lavatrice.MIN_TEMPERATURA
                        +" e "+Lavatrice.MAX_TEMPERATURA+" °C");
            }
        }

        else {
            ModificaStatoLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(erroreStringa);
        }

        lavatriceModificare.setTemperatura(temperatura);

    }

    private void modificaTempo() {

        var tempo=0;
        String voce;
        boolean controlloTempo;

        controlloTempo = true;

        ModificaStatoLavatriciMessaggi.inserimentoTempo();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controlloTempo=false;
            }
        }

        if(controlloTempo) {
            tempo=Integer.parseInt(voce);
            if(tempo<Lavatrice.MIN_TEMPO||tempo>Lavatrice.MAX_TEMPO) {
                ModificaStatoLavatriciMessaggi.erroreTempo();
                throw new LavatriceException(" Errore! Il tempo deve essere tra "+Lavatrice.MIN_TEMPO
                        +" e "+Lavatrice.MAX_TEMPO+" minuti");
            }
        }

        else {
            ModificaStatoLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(erroreStringa);
        }

        lavatriceModificare.setTempo(tempo);

    }

}
