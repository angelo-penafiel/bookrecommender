package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ValutazioniHashMap;
import bookrecommender.elaborazione.entities.valutazione.Valutazione;

import java.util.Scanner;

public class InserimentoValutazione {

    /**
     * Fa andare avanti il programma solo se il punteggio è fra 0 e 5
     * @param messaggio il messaggio da far visualizzare
     * @return il punteggio
     */
    private static String getPunteggio(String messaggio) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print(messaggio);
            int punteggio = in.nextInt();
            if (punteggio > 0 && punteggio <= 5) {
                return String.valueOf(punteggio);
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
    }

    /**
     * Controlla che sia rispettato il limite massimo di 256 caratteri
     * @param messaggio il messaggio da far visualizzare
     * @return il commento
     */
    private static String getCommento(String messaggio) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print(messaggio);
            String commento = in.nextLine();
            if (commento.length() <= 256) {
                return commento;
            } else {
                System.out.println("Il commento deve essere al massimo di 256 caratteri");
            }
        }
    }

    public static String in(String UserID, Libro libro) {

        ValutazioniHashMap val = ValutazioniHashMap.getInstance();

        if (val.hasValutazione(UserID, libro.getId().toString())) {
            System.out.print("Valutazione già esistente, premere qualsiasi tasto per uscire");
            new Scanner(System.in).nextLine();
            return null;
        }

        Scanner in = new Scanner(System.in);
        String[] score = new String[14]; // Array per contenere le valutazioni e i commenti
        score[0] = UserID;
        score[1] = libro.getId().toString();

        score[2] = getPunteggio("Inserisci il punteggio per lo stile (1..5): ");
        score[3] = getCommento("Inserisci un commento per lo stile: ");

        score[4] = getPunteggio("Inserisci il punteggio per il contenuto (1..5): ");
        score[5] = getCommento("Inserisci un commento per il contenuto: ");

        score[6] = getPunteggio("Inserisci il punteggio per la gradevolezza (1..5): ");
        score[7] = getCommento("Inserisci un commento per la gradevolezza: ");

        score[8] = getPunteggio("Inserisci il punteggio per l'originalità (1..5): ");
        score[9] = getCommento("Inserisci un commento per l'originalità: ");

        score[10] = getPunteggio("Inserisci il punteggio per l'edizione (1..5): ");
        score[11] = getCommento("Inserisci un commento per l'edizione: ");

        score[12] = getPunteggio("Inserisci la valutazione finale (1..5): ");
        score[13] = getCommento("Inserisci un commento per la valutazione finale: ");

        Valutazione.valuta(score, UserID, libro);

        return UserID;
    }

}
