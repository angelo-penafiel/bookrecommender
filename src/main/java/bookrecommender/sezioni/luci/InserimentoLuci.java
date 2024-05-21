
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.luci;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.LuceException;
import bookrecommender.elaborazione.factory.DispositivoEnum;
import bookrecommender.elaborazione.factory.DispositivoFactory;
import bookrecommender.elaborazione.services.LuceService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.luci.InserimentoLuciMessaggi;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InserimentoLuci {

    private Integer id;

    private String nome;

    private Boolean acceso;

    private final Scanner in=new Scanner(System.in);

    private final Utente utente;

    public InserimentoLuci(Utente utente) {

        this.utente=utente;

        NuovaSchermata.nuovaSchermata();
        Headers.inserimentoLuci(utente.getUsername());
        InserimentoLuciMessaggi.menu();

        int scelta = SceltaMenu.sceltaMenu(2);

        if(scelta ==1) {

            inserimentoId();
            registrazioneEsistente();
        }

        if(scelta ==2) {

            inserimentoDati();
            registrazioneNonEsistente();
        }

    }

    private void inserimentoId() {

        String stringa;
        boolean controllo;

        NuovaSchermata.nuovaSchermata();
        InserimentoLuciMessaggi.intestazione();

        controllo = true;

        InserimentoLuciMessaggi.inserimentoId();
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

            var luceService=new LuceService();

            boolean exists= luceService.existsById(id);

            if(!exists) {
                InserimentoLuciMessaggi.erroreLuceNonEsistente();
                throw new LuceException(" Errore! La luce non esiste");
            }
        }

        else {
            throw new LuceException(" Errore! L'id è un numero");
        }

    }

    private void registrazioneEsistente() {

        var luceService=new LuceService();
        luceService.addAssegnamento(id,utente.getId());
    }

    private void inserimentoDati() {

        inserimentoNome();

        inserimentoAcceso();
    }

    private void inserimentoNome() {

        NuovaSchermata.nuovaSchermata();
        InserimentoLuciMessaggi.intestazione();

        InserimentoLuciMessaggi.inserimentoNome();
        nome=in.nextLine();

        if(nome.length()< Luce.MIN_NOME
                ||nome.length()> Luce.MAX_NOME) {

            InserimentoLuciMessaggi.erroreLunghezzaNome();
            throw new LuceException(" Errore! Il nome deve essere tra" +
                    " "+ Luce.MIN_NOME+" e "+ Luce.MAX_NOME+" caratteri");
        }

        else {

            var luceService=new LuceService();

            var exists = luceService.existsByNome(nome, utente.getId());

            if(exists) {

                InserimentoLuciMessaggi.erroreNomeNonDisponibile();
                throw new LuceException(" Errore! Il nome è già stato inserito");
            }

        }

    }

    private void inserimentoAcceso() {

        String stringa;

        InserimentoLuciMessaggi.inserimentoAcceso();
        stringa=in.nextLine();

        acceso= "on".equalsIgnoreCase(stringa);
    }

    private void registrazioneNonEsistente() {

        List<Object> parametri = new ArrayList<>();
        parametri.add(nome);
        parametri.add(acceso);

        var dispositivo = DispositivoFactory.getDispositivo(DispositivoEnum.LUCE_SENZA_ID,parametri);
        var luce = (Luce) dispositivo;

        var luceService=new LuceService();
        luce.setId(luceService.add(luce,utente.getId()));
    }
}
