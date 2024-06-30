package bookrecommender.struttura.consigliati;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;
import bookrecommender.struttura.valutazione.InserimentoValutazione;
import bookrecommender.struttura.valutazione.ModificaValutazione;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import bookrecommender.elaborazione.entities.utils.CSVUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

public class MenuConsigliati {

  private int scelta;

  public MenuConsigliati(String userID) {

    boolean controllo;

    do {

        find(userID, header,"data/ConsigliLibri.dati.csv");


      controllo=true;

      //verifica se sono presenti libri consigliati






      NuovaSchermata.nuovaSchermata();
      if(true) {

        MenuConsigliatiMessaggi.menuConsigliati();
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


    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }

  public static String find(String toFind, String header, String path) {
    try {
      FileReader reader = new FileReader(path);
      CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
              .setHeader()
              .setSkipHeaderRecord(true)
              .build();
      CSVParser parser = new CSVParser(reader, csvFormat);
      for (CSVRecord record : parser) {
        if (record.get(header).equals(toFind)) return record.get(header);
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
/*
  private void caricamentoLibriConsigliati() throws IOException {

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");


    String[] HEADERS = {"id libro 1","id libro 2","id libro 3"};

    HashMap preferiti =CSVUtils.hashCsv("id Utente",HEADERS, "data/ConsigliLibri.dati.csv" );


    }
*/


  }







