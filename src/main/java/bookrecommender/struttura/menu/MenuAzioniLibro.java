package bookrecommender.struttura.menu;

import bookrecommender.elaborazione.dao.ConsigliatiDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatiDaoImpl;
import bookrecommender.elaborazione.entities.Consigliati;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuAzioniLibroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.consigliati.InserimentoConsigliati;
import bookrecommender.struttura.valutazione.MenuValutazione;
import java.io.IOException;

public class MenuAzioniLibro {

    private int scelta;

    public MenuAzioniLibro(String userID, Libro l) {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();

            Consigliati consigliato;

            ConsigliatiDao consigliatiDao=new ConsigliatiDaoImpl();

            try {
                consigliato=consigliatiDao.getByUserId(userID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

          if(consigliato==null) {
                try {
                    consigliatiDao.add(userID);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    consigliato=consigliatiDao.getByUserId(userID);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            boolean isLibroConsigliato=false;

            for(String libroConsigliato:consigliato.getLibriConsigliati()) {
                if(libroConsigliato.equals(l.getId().toString())) {
                    isLibroConsigliato=true;
                }
            }

            if("-1".equals(consigliato.getLibriConsigliati()[Consigliati.MAX_LIBRI_CONSIGLIATI-1])
                && !isLibroConsigliato) {

                MenuAzioniLibroMessaggi.menuLibroDaConsigliare();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

                if (scelta == 1) {

                    var menuValutazione = new MenuValutazione(userID, l);

                    if (menuValutazione.getScelta() == 3) {
                        controllo = false;
                    }

                    if (menuValutazione.getScelta() == 4) {
                        scelta = 3;
                    }

                    if (menuValutazione.getScelta() == 5) {
                        scelta = 4;
                    }

                }

                if (scelta == 2) {
                    var inserimentoConsigliati = new InserimentoConsigliati(userID, l);
                    controllo=false;
                }
            }

            else {

                MenuAzioniLibroMessaggi.menuLibroDaNonConsigliare();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);

                if (scelta == 1) {

                    var menuValutazione = new MenuValutazione(userID, l);

                    if (menuValutazione.getScelta() == 3) {
                        controllo = false;
                    }

                    if (menuValutazione.getScelta() == 4) {
                        scelta = 3;
                    }

                    if (menuValutazione.getScelta() == 5) {
                        scelta = 4;
                    }

                }

                if (scelta > 1) {
                    scelta++;
                }

            }

        } while (!controllo);

    }

    public int getScelta() {
        return scelta;
    }
}
