
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.LavatriceException;
import bookrecommender.elaborazione.factory.DispositivoEnum;
import bookrecommender.elaborazione.factory.DispositivoFactory;
import bookrecommender.elaborazione.services.LavatriceService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.lavatrici.InserimentoLavatriciMessaggi;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InserimentoLavatrici {

    private Integer id;

    private String nome;

    private Boolean acceso;

    private Boolean lavaggio;

    private Integer temperatura;

    private Integer tempo;

    private LocalDateTime inizio;

    private LocalDateTime fine;

    private final Scanner in=new Scanner(System.in);

    private final Utente utente;

    public InserimentoLavatrici(Utente utente) {

        this.utente=utente;

        NuovaSchermata.nuovaSchermata();
        Headers.inserimentoLavatrici(utente.getUsername());
        InserimentoLavatriciMessaggi.menu();

        int scelta = SceltaMenu.sceltaMenu(2);

        if(scelta ==1) {

            NuovaSchermata.nuovaSchermata();
            inserimentoId();
            registrazioneEsistente();
        }

        if(scelta ==2) {

            NuovaSchermata.nuovaSchermata();
            inserimentoDati();
            registrazioneNonEsistente();
        }

    }

    private void inserimentoId() {

        String stringa;
        boolean controllo;

        InserimentoLavatriciMessaggi.intestazione();

        controllo = true;

        InserimentoLavatriciMessaggi.inserimentoId();
        stringa=in.nextLine();

        for(var i=0;i<stringa.length();i++) {
            if(!Character.isDigit(stringa.charAt(i))) {
                controllo=false;
            }
        }

        if(stringa.isEmpty()) {
            controllo=false;
        }

        if(controllo) {

            id=Integer.parseInt(stringa);

            var lavatriceService=new LavatriceService();

            boolean exists = lavatriceService.existsById(id);

            if(!exists) {

                InserimentoLavatriciMessaggi.erroreLavatriceNonEsistente();
                throw new LavatriceException(" Errore! La lavatrice non esiste");
            }
        }

        else {
            throw new LavatriceException(" Errore! L'id è un numero");
        }

    }

    private void registrazioneEsistente() {

        var lavatriceService=new LavatriceService();
        lavatriceService.addAssegnamento(id,utente.getId());
    }

    private void inserimentoDati() {

        inserimentoNome();

        inserimentoAcceso();

        if(Boolean.TRUE.equals(acceso)) {
            inserimentoLavaggio();
        }

        else {

            lavaggio=false;
            temperatura=0;
            tempo=0;
        }

    }

    private void inserimentoNome() {

        InserimentoLavatriciMessaggi.intestazione();

        InserimentoLavatriciMessaggi.inserimentoNome();
        nome=in.nextLine();

        if(nome.length()< Lavatrice.MIN_NOME
                ||nome.length()> Lavatrice.MAX_NOME) {

            InserimentoLavatriciMessaggi.erroreLunghezzaNome();
            throw new LavatriceException(" Errore! Il nome deve essere tra" +
                    " "+ Lavatrice.MIN_NOME+" e "+ Lavatrice.MAX_NOME+" caratteri");
        }

        else {

            var lavatriceService=new LavatriceService();

            boolean exists = lavatriceService.existsByNome(nome, utente.getId());

            if(exists) {
                InserimentoLavatriciMessaggi.erroreNomeNonDisponibile();
                throw new LavatriceException(" Errore! Il nome è già stato inserito\n");
            }

        }

    }

    private void inserimentoAcceso() {

        String stringa;

        InserimentoLavatriciMessaggi.inserimentoAcceso();
        stringa=in.nextLine();

        acceso = "on".equalsIgnoreCase(stringa);
    }

    private void inserimentoLavaggio() {

        inserimentoStatoLavaggio();

        if(Boolean.TRUE.equals(lavaggio)) {

            inserimentoTemperatura();

            inserimentoTempo();

            inizio=LocalDateTime.now();
            fine=inizio.plusMinutes(tempo);
        }

        else {

            inizio=null;
            fine=null;
            temperatura=0;
            tempo=0;
        }
    }

    private void inserimentoStatoLavaggio() {

        String stringa;

        InserimentoLavatriciMessaggi.inserimentoStatoLavaggio();
        stringa=in.nextLine();

        lavaggio = "si".equalsIgnoreCase(stringa);
    }

    private void inserimentoTemperatura() {

        String voce;
        boolean controllo;

        controllo = true;

        InserimentoLavatriciMessaggi.inserimentoTemperatura();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controllo=false;
            }
        }

        if(controllo) {
            temperatura=Integer.parseInt(voce);
            if(temperatura<Lavatrice.MIN_TEMPERATURA||temperatura>Lavatrice.MAX_TEMPERATURA) {

                InserimentoLavatriciMessaggi.erroreTemperatura();
                throw new LavatriceException(" Errore! La temperatura deve essere tra "+Lavatrice.MIN_TEMPERATURA
                        +" e "+Lavatrice.MAX_TEMPERATURA+" °C");
            }
        }

        else {
            InserimentoLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(" Errore! Inserire un numero!");
        }

    }

    private void inserimentoTempo() {

        String voce;
        boolean controllo;

        controllo = true;

        InserimentoLavatriciMessaggi.inserimentoTempo();
        voce=in.nextLine();

        for(var i=0;i<voce.length();i++) {
            if(!Character.isDigit(voce.charAt(i))) {
                controllo=false;
            }
        }

        if(controllo) {
            tempo=Integer.parseInt(voce);
            if(tempo<Lavatrice.MIN_TEMPO||tempo>Lavatrice.MAX_TEMPO) {
                InserimentoLavatriciMessaggi.erroreTempo();
                throw new LavatriceException(" Errore! Il tempo deve essere tra "+Lavatrice.MIN_TEMPO
                        +" e "+Lavatrice.MAX_TEMPO+" minuti");
            }
        }

        else {
            InserimentoLavatriciMessaggi.erroreStringa();
            throw new LavatriceException(" Errore! Inserire un numero!");
        }

    }

    private void registrazioneNonEsistente() {

        List<Object> parametri = new ArrayList<>();
        parametri.add(nome);
        parametri.add(acceso);
        parametri.add(temperatura);
        parametri.add(tempo);
        parametri.add(inizio);
        parametri.add(fine);

        var dispositivo = DispositivoFactory.getDispositivo(DispositivoEnum.LAVATRICE_SENZA_ID,parametri);

        var lavatrice = (Lavatrice) dispositivo;

        var lavatriceService=new LavatriceService();
        lavatrice.setId(lavatriceService.add(lavatrice, utente.getId()));
    }
}
