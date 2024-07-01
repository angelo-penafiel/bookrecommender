package bookrecommender.elaborazione.entities.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Scanner;

/**
 * Questa classe si occupa della scrittura e della logica dietro le valutazioni
 *
 * @author Leonardo Basso
 * @see bookrecommender.struttura.valutazione.MenuValutazione
 */
public class Valutazione {

    /**
     * Questo metodo inserisce in un file .csv i dati delle valutazioni dato un libro
     *
     * @param UserID l'UserID dell'utente che fa la valutazione
     * @param libro  il libro da valutare
     * @see bookrecommender.struttura.valutazione.MenuValutazione
     */
    public static void valuta(String[] valutazioni, String UserID, Libro libro) {
        try {
            ValutazioniHashMap val = ValutazioniHashMap.getInstance();

            BufferedWriter writer = new BufferedWriter(new FileWriter("data/ValutazioniLibri.dati.csv", true));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            csvPrinter.printRecord((Object[]) valutazioni);

            csvPrinter.close();
            writer.close();

            val.add(UserID, libro.getId().toString(), valutazioni); // Aggiorna la HashMap

        } catch (IOException e) {
            throw new RuntimeException("Errore durante la scrittura nel file CSV", e);
        }
    }

}
