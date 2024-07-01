package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ConsigliatiHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

import java.io.IOException;
import java.util.Arrays;

public class MenuConsigliati {

    private int scelta;

    public MenuConsigliati(String userID, Libro l) {

        boolean controllo=true;
        ConsigliatiHashMap cons = ConsigliatiHashMap.getInstance();
        String control="-1";
        String[] utenti = cons.getValues(userID);







    do {
        if(true==control.equals(utenti[3])) {
            //  Arrays.toString(utenti)
        try {
            controllo = true;

            //verifica se sono presenti libri consigliati

            NuovaSchermata.nuovaSchermata();

            if (true) {

                MenuConsigliatiMessaggi.menuConsigliati();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

                if (scelta == 1) {

                    var inserimentoConsigliati = new InserimentoConsigliati(userID, l);

                    controllo = false;

                } else if (scelta == 2) {

                    var selezioneConsigliati = new SelezioneConsigliati();

                    var inserimentoConsigliati = new InserimentoConsigliati(userID, l);

                    controllo = false;

                }
            }
        } catch (IOException e) {
            System.out.println(" ");
        }

    }
        else
        {
            System.out.println("Hai già consigliato 3 libri non puoi consigliarne altri");
        }


    } while (!controllo);









    }
    public int getScelta () {
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

