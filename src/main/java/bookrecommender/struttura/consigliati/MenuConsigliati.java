package bookrecommender.struttura.consigliati;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;
import bookrecommender.struttura.valutazione.InserimentoValutazione;
import bookrecommender.struttura.valutazione.ModificaValutazione;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import bookrecommender.elaborazione.entities.utils.CSVUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

public class MenuConsigliati {

  private int scelta;

  public MenuConsigliati() {

    boolean controllo;

    do {




      controllo=true;

      //verifica se sono presenti libri consigliati






      NuovaSchermata.nuovaSchermata();
      if(true) {

        MenuConsigliatiMessaggi.menuConsigliatiPresenti();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

        if(scelta==1) {

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

        else if(scelta==2) {

          var selezioneConsigliati=new SelezioneConsigliati();

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

      }

      else {

        MenuConsigliatiMessaggi.menuConsigliatiAssenti();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

        if(scelta==1) {

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

        if(scelta>=2&&scelta<=4) {
          scelta++;
        }
      }

    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }



  private void caricamentoLibriConsigliati() throws IOException {

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");


    String[] HEADERS = {"id libro 1","id libro 2","id libro 3"};

    HashMap preferiti =CSVUtils.hashCsv("id Utente",HEADERS, "data/ConsigliLibri.dati.csv" );


    }



  }







