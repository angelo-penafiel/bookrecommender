package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ConsigliatiHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

import java.util.Arrays;

public class MenuConsigliati {

    private int scelta;

    public MenuConsigliati(String userID, Libro l) {

        boolean controllo;

        do {

            //find(userID, HEADERS,"data/ConsigliLibri.dati.csv");

            ConsigliatiHashMap cons = ConsigliatiHashMap.getInstance();

            String[] utenti = cons.getValues(userID);
            System.out.println(Arrays.toString(utenti));
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







