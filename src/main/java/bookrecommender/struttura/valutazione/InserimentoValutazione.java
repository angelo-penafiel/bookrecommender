package bookrecommender.struttura.valutazione;

import bookrecommender.elaborazione.entities.Libro;

import java.util.Scanner;

public class InserimentoValutazione {
    static String UserID;
    static Libro libro;

    public InserimentoValutazione(String id, Libro l) {
        UserID = id;
        libro = l;
    }

    public static String[] in(String UserID,Libro libro) {
        Scanner in = new Scanner(System.in);
        String[] score = new String[6];

        while (true) { // Stile
            System.out.print("Inserisci il punteggio per lo stile (1..5): ");
            int stile = in.nextInt();
            if (stile > 0 && stile <= 5) {
                score[0] = String.valueOf(stile);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
        while (true) { // Contenuto
            System.out.print("Inserisci il punteggio per il contenuto (1..5): ");
            int contenuto = in.nextInt();
            if (contenuto > 0 && contenuto <= 5) {
                score[1] = String.valueOf(contenuto);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
        while (true) { // Contenuto
            System.out.print("Inserisci il punteggio per la gradevolezza (1..5): ");
            int gradevolezza = in.nextInt();
            if (gradevolezza > 0 && gradevolezza <= 5) {
                score[2] = String.valueOf(gradevolezza);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
        while (true) { // Originalità
            System.out.print("Inserisci il punteggio per l'originalità (1..5): ");
            int originalita = in.nextInt();
            if (originalita > 0 && originalita <= 5) {
                score[3] = String.valueOf(originalita);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
        while (true) { // Edizione
            System.out.print("Inserisci il punteggio per l'edizione (1..5): ");
            int edizione = in.nextInt();
            if (edizione > 0 && edizione <= 5) {
                score[4] = String.valueOf(edizione);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }
        while (true) { // Edizione
            System.out.print("Inserisci la valutazione finale (1..5): ");
            int finale = in.nextInt();
            if (finale > 0 && finale <= 5) {
                score[5] = String.valueOf(finale);
                break;
            } else {
                System.out.println("Il punteggio deve essere da 1 a 5");
            }
        }

        return score;
    }


}
