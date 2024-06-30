package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;
import bookrecommender.struttura.valutazione.InserimentoValutazione;
import bookrecommender.struttura.valutazione.ModificaValutazione;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import bookrecommender.elaborazione.entities.utils.CSVToHashMap;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class MenuConsigliati {

  private int scelta;

  public MenuConsigliati(String userID, Libro l) {

    boolean controllo;
    String[] HEADERS = {"UserID","id libro consigliato 1","id libro consigliato 2","id libro consigliato 3"};




    do {

        //find(userID, HEADERS,"data/ConsigliLibri.dati.csv");

      CSVToHashMap cons = CSVToHashMap.getInstance();

       // Controlla che l'HashMap esista o meno
        cons.hashCsv("UserID", HEADERS, "data/ConsigliLibri.dati.csv");


   String[] utenti=cons.getValues(userID);
      System.out.println(Arrays.toString(utenti));
      controllo=true;

      //verifica se sono presenti libri consigliati






      NuovaSchermata.nuovaSchermata();
      if(true) {

        MenuConsigliatiMessaggi.menuConsigliati();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

        if(scelta==1) {

          var inserimentoConsigliati=new InserimentoConsigliati(userID,l);

          controllo=false;

        }

        else if(scelta==2) {

          var inserimentoConsigliati=new InserimentoConsigliati(userID,l);

          controllo=false;

        }

      }


    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }
/*
  public static String find(String toFind, String[] header, String path) {
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


*/


  }







