package bookrecommender.elaborazione.entities.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.valutazione.InserimentoValutazione;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class Valutazione {

    public static String valuta(String UserID, Libro libro) {
        try {
            String[] valutazioni = InserimentoValutazione.in(UserID, libro);
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/ValutazioniLibri.dati.csv", true));

            writer.write(UserID + "," + libro.getId() + "," + valutazioni[0] + "," + valutazioni[1] + "," +
                    valutazioni[2] + "," + valutazioni[3] + "," + valutazioni[4] + "," + valutazioni[5] + "\n");
            writer.close();

            return UserID; // UserID
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
