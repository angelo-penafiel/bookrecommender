
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroMessaggi;
import bookrecommender.sezioni.lavatrici.MenuLavatrici;
import bookrecommender.sezioni.luci.MenuLuci;
import bookrecommender.sezioni.menu.SceltaMenu;
import bookrecommender.sezioni.notifiche.GestioneNotifiche;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RicercaLibro {

    public static Integer MAX_RISULTATI_PAGINA=7;

    private int scelta;

    private Utente utente;

    private Libro libro;

    private List<String> autori;

    private String titolo;

    private String autore;

    private Integer annoPubblicazione;

    private List<Integer> libriTrovati;

    private List<String> autoriTrovati;

    private List<String> autoriTrovatiNuovo;

    private List<Integer> annoTrovati;

    private List<List<Object>> opzioniTitolo;

    private Integer libroSelezionato;

    private String autoreSelezionato;

    private Integer annoSelezionato;

    public RicercaLibro(Utente utente) {

        this.utente=utente;

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menu(utente.getUsername());

            scelta = SceltaMenu.sceltaMenu(5);

            if(scelta==1) {
                controllo=luci();
            }

            if(scelta==2) {
                controllo=lavatrici();
            }

            if(scelta==3) {
                controllo=notifiche();
            }

            if(scelta==4) {
                //log out
                NuovaSchermata.nuovaSchermata();
            }

        } while(!controllo);
    }

    public RicercaLibro(int continuaSenzaRegistrazione) {

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            RicercaLibroMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenu.sceltaMenu(6);

            int modalitaRicerca=scelta;

            if(scelta==1) {
                ricercaLibroTitolo(modalitaRicerca);
            }

            if(scelta==2) {
                ricercaLibroAutore(modalitaRicerca);
            }

            if(scelta==3) {
                ricercaLibroAutoreAnnoPubblicazione(modalitaRicerca);
            }

            if(scelta==4) {

            }

            if(scelta==5) {

            }

        } while(!controllo);
    }

    private boolean luci() {

        var controllo=true;

        var menuLuci = new MenuLuci(utente);

        if(menuLuci.getScelta()==4) {
            controllo=false;
        }

        if(menuLuci.getScelta()==5) {
            //log out
            scelta=4;
        }

        if(menuLuci.getScelta()==6) {
            //esci dal programma
            scelta=5;
        }

        return controllo;
    }

    private boolean lavatrici() {

        var controllo=true;

        var menuLavatrici=new MenuLavatrici(utente);

        if(menuLavatrici.getScelta()==4) {
            controllo=false;
        }

        if(menuLavatrici.getScelta()==5) {
            //log out
            scelta=4;
        }

        if(menuLavatrici.getScelta()==6) {
            //esci dal programma
            scelta=5;
        }

        return controllo;
    }

    private boolean notifiche() {

        var controllo=true;

        var notifiche=new GestioneNotifiche(utente);      //notifiche

        if(notifiche.getScelta()==1) {
            controllo=false;
        }

        if(notifiche.getScelta()==2) {
            scelta=4;
        }

        if(notifiche.getScelta()==3) {
            scelta=5;
        }

        return controllo;
    }

    private void caricamentoLibri() throws IOException {

        this.autori=new ArrayList<>();

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
        "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();
            String authors=record.get("Authors").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));;
            String editore=record.get("Publisher").toLowerCase();
            String categories=record.get("Category").toLowerCase();

            List<String> autori=new ArrayList<>();
            List<String> categorie=new ArrayList<>();

            authors=authors.trim();
            categories=categories.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {
                    autori.add(authors.substring(j,i));

                    //caricamento lista globale autori
                    boolean controllo=true;

                    for(String autore:this.autori) {
                        if(autore.equalsIgnoreCase(authors.substring(j,i))) {
                            controllo=false;
                        }
                    }

                    if(controllo) {
                        this.autori.add(authors.substring(j,i));
                    }

                    j=i+2;
                }

                if(',' == authors.charAt(i)&&i-j<=2) {
                    j=i+2;
                }

                //per rimuovere l'and di: autore1, and autore2
                if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                    j=i;
                }

                //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

            }

            if(authors.length()-j>1) autori.add(authors.substring(j));

            j=0;

            for(int i=0;i<categories.length();i++) {
                if(',' == categories.charAt(i)&&i-j>2) {
                    categorie.add(categories.substring(j,i-1));
                    j=i+2;
                }

                if(',' == categories.charAt(i)&&i-j<=2) {
                    j=i+2;
                }
            }

            if(categories.length()-j>3) categorie.add(categories.substring(j));

            //libri.add(new Libro(titolo, autori, annoPubblicazione, editore, categorie));

        }
    }

    private void ricercaLibroTitolo(int modalitaRicerca) {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.intestazioneInserimentoTitolo();

        inserimentoTitolo();

        libriTrovati=new ArrayList<>();
        cercaLibro(modalitaRicerca);

        opzioniTitolo=new ArrayList<>();
        try {
            caricamentoOpzioniTitolo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();

        if(opzioniTitolo.size()>MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroMessaggi.stampaOpzioniTitoloPagina(opzioniTitolo,paginaCorrente);
                libroSelezionato=sceltaMenuOpzioniPagina(paginaCorrente,
                        opzioniTitolo.size()/MAX_RISULTATI_PAGINA+1,opzioniTitolo.size());

                if(libroSelezionato==-1||libroSelezionato==-2) {
                    controllo=false;
                }

                if(libroSelezionato==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(libroSelezionato==-2) {
                    paginaCorrente=paginaCorrente-1;
                }

                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroMessaggi.stampaOpzioniTitolo(opzioniTitolo);
            libroSelezionato=SceltaMenu.sceltaMenu(opzioniTitolo.size());
            NuovaSchermata.nuovaSchermata();
        }

        libroSelezionato=libriTrovati.get(libroSelezionato-1);

        try {
            caricamentoLibro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(libro.getTitolo());
        System.out.println(libro.getAutori());
        System.out.println(libro.getAnnoPubblicazione());
        System.out.println(libro.getEditore());
        System.out.println(libro.getCategorie());

    }

    private void inserimentoTitolo() {

        boolean controllo;
        Scanner in=new Scanner(System.in);

        do {

            controllo=true;
            RicercaLibroMessaggi.inserimentoTitolo();
            titolo=in.nextLine();

            if(titolo.length()<Libro.MIN_TITOLO||titolo.length()>Libro.MAX_TITOLO) {
                controllo=false;
                RicercaLibroMessaggi.erroreInserimentoTitolo();
            }

        } while(!controllo);

        titolo=titolo.toLowerCase();
    }

    private void cercaLibro(int modalitaRicerca) {

        if(modalitaRicerca==1) {
            try {
                cercaLibroTitolo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(modalitaRicerca==2) {
            try {
                cercaLibroAutore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(modalitaRicerca==3) {
            try {
                cercaLibroAutoreAnnoPubblicazione();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void caricamentoOpzioniTitolo() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int i=0;

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));;

            for(Integer libroTrovato:libriTrovati) {
                if(libroTrovato==i) {

                    List<Object> oggetti=new ArrayList<>();
                    oggetti.add(titolo);
                    oggetti.add(annoPubblicazione);

                    opzioniTitolo.add(oggetti);
                }
            }

            i++;

        }

    }

    private int sceltaMenuOpzioniPagina(int paginaCorrente, int pagineTotali, int opzioniTotali) {

        var scelta = 0;
        String voce;
        boolean controllo;
        var in=new Scanner(System.in);

        do {
            controllo=true;

            RicercaLibroMessaggi.inserimentoScelta(paginaCorrente,pagineTotali);
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

                int max;

                if(paginaCorrente+1==pagineTotali) {
                    max=opzioniTotali;
                }

                else {
                    max=paginaCorrente*MAX_RISULTATI_PAGINA+MAX_RISULTATI_PAGINA;
                }

                if(scelta<paginaCorrente*MAX_RISULTATI_PAGINA+1 ||
                        scelta>max) {
                    controllo=false;
                    RicercaLibroMessaggi.erroreScelte(paginaCorrente*MAX_RISULTATI_PAGINA+1,max);
                }
            }

            else {

                if(paginaCorrente+1<pagineTotali&&paginaCorrente==0) {
                    if ("d".equalsIgnoreCase(voce)) {
                        controllo=true;
                        scelta=-1;
                    }
                }

                if(paginaCorrente+1<pagineTotali&&paginaCorrente!=0) {
                    if ("d".equalsIgnoreCase(voce)||"a".equalsIgnoreCase(voce)) {
                        controllo=true;

                        if ("d".equalsIgnoreCase(voce)) {
                            scelta=-1;
                        }

                        if ("a".equalsIgnoreCase(voce)) {
                            scelta=-2;
                        }
                    }
                }

                if(paginaCorrente+1==pagineTotali) {
                    if ("a".equalsIgnoreCase(voce)) {
                        controllo=true;
                        scelta=-2;
                    }
                }

                if(!controllo) {
                    RicercaLibroMessaggi.erroreStringa();
                }
            }

        } while(!controllo);

        return scelta;
    }

    private void cercaLibroTitolo() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int i=0;

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();

            if(titolo.contains(this.titolo)) {
                libriTrovati.add(i);
            }

            i++;
        }
    }

    private void cercaLibroAutore() throws IOException {

        //se seleziona angela shelf i risultati saranno solo angela shelf e non rientrerà angela shelf and walker

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int cont=0;

        for (CSVRecord record : records) {

            String authors=record.get("Authors").toLowerCase();
            authors=authors.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {
                    if(authors.substring(j,i).equals(autoreSelezionato)){
                        libriTrovati.add(cont);
                    }
                    j=i+2;
                }

                if(',' == authors.charAt(i)&&i-j<=2) {
                    j=i+2;
                }

                //per rimuovere l'and di: autore1, and autore2
                if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                    j=i;
                }

                //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

            }

            if(authors.length()-j>1) {
                if(authors.substring(j).equals(autoreSelezionato)){
                    libriTrovati.add(cont);
                }
            }

            cont++;
        }
    }

    private void ricercaLibroAutore(int modalitaRicerca) {

        HashMap<String, Integer> autori;

        try {
            autori=caricamentoAutori();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.intestazioneInserimentoAutore();

        inserimentoAutore();

        autoriTrovati=new ArrayList<>();
        try {
            cercaAutore(autori);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Integer autoreSelezionatoInt;
        NuovaSchermata.nuovaSchermata();

        if(autoriTrovati.size()>MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroMessaggi.stampaOpzioniAutorePagina(autoriTrovati,paginaCorrente);
                autoreSelezionatoInt=sceltaMenuOpzioniPagina(paginaCorrente,
                        autoriTrovati.size()/MAX_RISULTATI_PAGINA+1,autoriTrovati.size());

                if(autoreSelezionatoInt==-1||autoreSelezionatoInt==-2) {
                    controllo=false;
                }

                if(autoreSelezionatoInt==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(autoreSelezionatoInt==-2) {
                    paginaCorrente=paginaCorrente-1;
                }
                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroMessaggi.stampaOpzioniAutore(autoriTrovati);
            autoreSelezionatoInt=SceltaMenu.sceltaMenu(autoriTrovati.size());
            NuovaSchermata.nuovaSchermata();
        }

        autoreSelezionato=autoriTrovati.get(autoreSelezionatoInt-1);

        libriTrovati=new ArrayList<>();
        cercaLibro(modalitaRicerca);

        opzioniTitolo=new ArrayList<>();
        try {
            caricamentoOpzioniTitolo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();

        if(opzioniTitolo.size()>MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroMessaggi.stampaOpzioniTitoloPagina(opzioniTitolo,paginaCorrente);
                libroSelezionato=sceltaMenuOpzioniPagina(paginaCorrente,
                        opzioniTitolo.size()/MAX_RISULTATI_PAGINA+1,opzioniTitolo.size());

                if(libroSelezionato==-1||libroSelezionato==-2) {
                    controllo=false;
                }

                if(libroSelezionato==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(libroSelezionato==-2) {
                    paginaCorrente=paginaCorrente-1;
                }

                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroMessaggi.stampaOpzioniTitolo(opzioniTitolo);
            libroSelezionato=SceltaMenu.sceltaMenu(opzioniTitolo.size());
            NuovaSchermata.nuovaSchermata();
        }

        libroSelezionato=libriTrovati.get(libroSelezionato-1);

        try {
            caricamentoLibro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(libro.getTitolo());
        System.out.println(libro.getAutori());
        System.out.println(libro.getAnnoPubblicazione());
        System.out.println(libro.getEditore());
        System.out.println(libro.getCategorie());

    }

    private HashMap<String, Integer> caricamentoAutori() throws IOException {

        HashMap<String, Integer> autori = new HashMap<>();

        Reader in = new FileReader("data/Libri.dati.csv");

        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for (CSVRecord record : records) {

            String authors=record.get("Authors").toLowerCase();
            authors=authors.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {

                    //caricamento lista globale autori
                    boolean controllo=true;

                    if(autori.containsKey(authors.substring(j,i))){
                        controllo=false;
                    }

                    if(controllo) {
                        autori.put(authors.substring(j,i),0);
                    }

                    j=i+2;
                }

                if(',' == authors.charAt(i)&&i-j<=2) {
                    j=i+2;
                }

                //per rimuovere l'and di: autore1, and autore2
                if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                    j=i;
                }

                //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

            }

            if(authors.length()-j>1) {

                //caricamento lista globale autori
                boolean controllo=true;

                if(autori.containsKey(authors.substring(j))){
                    controllo=false;
                }

                if(controllo) {
                    autori.put(authors.substring(j),0);
                }
            }
        }

        return autori;
    }

    private void inserimentoAutore() {

        boolean controllo;
        Scanner in=new Scanner(System.in);

        do {

            controllo=true;
            RicercaLibroMessaggi.inserimentoAutore();
            autore=in.nextLine();

            if(!"".equals(autore)) {
                if(autore.length()<Libro.MIN_AUTORE_CARATTERI||autore.length()>Libro.MAX_AUTORE_CARATTERI) {
                    controllo=false;
                    RicercaLibroMessaggi.erroreInserimentoAutore();
                }
            }

        } while(!controllo);

        autore=autore.toLowerCase();
    }

    private void cercaAutore(HashMap<String, Integer> autori) throws IOException {

        for (String autore : autori.keySet()) {
            if (autore.contains(this.autore)) {
                autoriTrovati.add(autore);
            }
        }
    }

    private void caricamentoLibro() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        CSVRecord record = csvFormat.parse(in).getRecords().get(libroSelezionato);

        String titolo=record.get("Title").toLowerCase();
        String authors=record.get("Authors").toLowerCase();
        Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));;
        String editore=record.get("Publisher").toLowerCase();
        String categories=record.get("Category").toLowerCase();

        List<String> autori=new ArrayList<>();
        List<String> categorie=new ArrayList<>();

        authors=authors.trim();
        categories=categories.trim();

        int j=3;

        for(int i=0;i<authors.length();i++) {
            if(',' == authors.charAt(i)&&i-j>2) {
                autori.add(authors.substring(j,i));
                j=i+2;
            }

            if(',' == authors.charAt(i)&&i-j<=2) {
                j=i+2;
            }

            //per rimuovere l'and di: autore1, and autore2
            if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                j=i;
            }

            //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

        }

        if(authors.length()-j>1) autori.add(authors.substring(j));

        j=0;

        for(int i=0;i<categories.length();i++) {
            if(',' == categories.charAt(i)&&i-j>2) {
                categorie.add(categories.substring(j,i-1));
                j=i+2;
            }

            if(',' == categories.charAt(i)&&i-j<=2) {
                j=i+2;
            }
        }

        if(categories.length()-j>3) categorie.add(categories.substring(j));

        libro=new Libro(titolo,autori,annoPubblicazione,editore,categorie);

    }

    private void ricercaLibroAutoreAnnoPubblicazione(int modalitaRicerca) {

        //ci sono più angela 2006

        HashMap<String, Integer> autori;

        try {
            autori=caricamentoAutori();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.intestazioneInserimentoAutore();

        inserimentoAutore();

        inserimentoAnnoPubblicazione();

        autoriTrovati=new ArrayList<>();
        annoTrovati=new ArrayList<>();
        try {
            cercaAutore(autori);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(autoriTrovati.size()>0) {

            autoriTrovatiNuovo=new ArrayList<>();
            try {
                cercaAnnoPubblicazione();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Integer autoreSelezionatoInt;
            NuovaSchermata.nuovaSchermata();

            if(autoriTrovatiNuovo.size()>MAX_RISULTATI_PAGINA) {

                int paginaCorrente=0;
                boolean controllo;

                do {
                    controllo=true;

                    RicercaLibroMessaggi.stampaOpzioniAutoreAnnoPagina(autoriTrovatiNuovo,annoTrovati,paginaCorrente);
                    autoreSelezionatoInt=sceltaMenuOpzioniPagina(paginaCorrente,
                            autoriTrovatiNuovo.size()/MAX_RISULTATI_PAGINA+1,autoriTrovatiNuovo.size());

                    if(autoreSelezionatoInt==-1||autoreSelezionatoInt==-2) {
                        controllo=false;
                    }

                    if(autoreSelezionatoInt==-1) {
                        paginaCorrente=paginaCorrente+1;
                    }

                    if(autoreSelezionatoInt==-2) {
                        paginaCorrente=paginaCorrente-1;
                    }

                    NuovaSchermata.nuovaSchermata();

                } while (!controllo);
            }

            else {
                RicercaLibroMessaggi.stampaOpzioniAutoreAnno(autoriTrovatiNuovo,annoTrovati);
                autoreSelezionatoInt=SceltaMenu.sceltaMenu(autoriTrovatiNuovo.size());
                NuovaSchermata.nuovaSchermata();
            }

            autoreSelezionato=autoriTrovatiNuovo.get(autoreSelezionatoInt-1);
            annoSelezionato=annoTrovati.get(autoreSelezionatoInt-1);

            libriTrovati=new ArrayList<>();
            cercaLibro(modalitaRicerca);

            opzioniTitolo=new ArrayList<>();
            try {
                caricamentoOpzioniTitolo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(opzioniTitolo.size()>MAX_RISULTATI_PAGINA) {

                int paginaCorrente=0;
                boolean controllo;

                do {
                    controllo=true;

                    RicercaLibroMessaggi.stampaOpzioniTitoloPagina(opzioniTitolo,paginaCorrente);
                    libroSelezionato=sceltaMenuOpzioniPagina(paginaCorrente,
                            opzioniTitolo.size()/MAX_RISULTATI_PAGINA+1,opzioniTitolo.size());

                    if(libroSelezionato==-1||libroSelezionato==-2) {
                        controllo=false;
                    }

                    if(libroSelezionato==-1) {
                        paginaCorrente=paginaCorrente+1;
                    }

                    if(libroSelezionato==-2) {
                        paginaCorrente=paginaCorrente-1;
                    }

                } while (!controllo);
            }

            else {
                RicercaLibroMessaggi.stampaOpzioniTitolo(opzioniTitolo);
                libroSelezionato=SceltaMenu.sceltaMenu(opzioniTitolo.size());
            }

            libroSelezionato=libriTrovati.get(libroSelezionato-1);

            try {
                caricamentoLibro();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(libro.getTitolo());
            System.out.println(libro.getAutori());
            System.out.println(libro.getAnnoPubblicazione());
            System.out.println(libro.getEditore());
            System.out.println(libro.getCategorie());

        }

    }

    private void inserimentoAnnoPubblicazione() {

        boolean controllo;
        String voce;
        Scanner in=new Scanner(System.in);

        LocalDateTime localDateTime=LocalDateTime.now();
        int annoCorrente=localDateTime.getYear();

        do {

            controllo=true;
            RicercaLibroMessaggi.inserimentoAnnoPubblicazione();
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
                annoPubblicazione=Integer.parseInt(voce);
                if(annoPubblicazione<Libro.MIN_ANNO_PUBBLICAZIONE||annoPubblicazione>annoCorrente) {
                    controllo=false;
                    RicercaLibroMessaggi.erroreInserimentoAnnoPubblicazione(annoCorrente);
                }
            }

            else {
                SceltaMenuMessaggi.erroreStringa();
            }

        } while(!controllo);

    }

    private void cercaAnnoPubblicazione() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");

        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for(String autore:autoriTrovati) {
            for (CSVRecord record : records) {

                String authors=record.get("Authors").toLowerCase();
                Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));

                authors=authors.trim();

                if(annoPubblicazione.equals(this.annoPubblicazione)) {
                    int j=3;

                    for(int i=0;i<authors.length();i++) {
                        if(',' == authors.charAt(i)&&i-j>2) {
                            if(authors.substring(j,i).equals(autore)){
                                autoriTrovatiNuovo.add(autore);
                                annoTrovati.add(annoPubblicazione);
                            }

                            j=i+2;
                        }

                        if(',' == authors.charAt(i)&&i-j<=2) {
                            j=i+2;
                        }

                        //per rimuovere l'and di: autore1, and autore2
                        if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                            j=i;
                        }

                        //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

                    }

                    if(authors.length()-j>1) {
                        if(authors.substring(j).equals(autore)){
                            autoriTrovatiNuovo.add(autore);
                            annoTrovati.add(annoPubblicazione);
                        }
                    }
                }
            }
        }
    }

    private void cercaLibroAutoreAnnoPubblicazione() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int i=0;

        for (CSVRecord record : records) {

            String authors=record.get("Authors").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));

            if(authors.contains(autoreSelezionato)&&annoPubblicazione.equals(annoSelezionato)) {
                libriTrovati.add(i);
            }

            i++;
        }
    }

    public int getScelta() {
        return scelta;
    }

}
