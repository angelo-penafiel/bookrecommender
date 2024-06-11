package bookrecommender.interfaccia.valutazioni;
import java.util.Scanner;

/** La classe Valutazione (che cosa fa)
*
 * @author Giulia Magni, Leonardo Basso
 * @see bookrecommender.elaborazione.entities.valutazioni.Valutazioni;
* */
public class Valutazioni {
        String[] categorie = {"Stile", "Contenuto", "Gradevolezza", "Originalita'", "Edizione", "VotoFinale"};

    /**
     * Questo metodo inserisce in un'array le valutazioni
     * @return Un'array che salva: {@code [0] Stile}, {@code [1] Contenuto}, {@code [2] Gradevolezza}, {@code [3] Originalita'}, {@code [4] Edizione}, {@code [5] VotoFinale}
     * */
        public static int[] in() {
            int[] Valutazione = new int[6];
            System.out.print("Inserisci la valutazione stile: ");
            Valutazione[0]=check();

            System.out.print("Inserisci la valutazione contenuto: ");
            Valutazione[1]=check();

            System.out.print("Inserisci la valutazione gradevolezza: ");
            Valutazione[2]=check();

            System.out.print("Inserisci la valutazione originalita': ");
            Valutazione[3]=check();

            System.out.print("Inserisci la valutazione edizione: ");
            Valutazione[4]=check();

            Valutazione[5]=(Valutazione[0]+Valutazione[1]+Valutazione[2]+Valutazione[3]+Valutazione[4])%5;

            return Valutazione;
        }

    /**
     * Questo metodo controlla se i valori inseriti sono compresi tra 1 e 5
     * @return il numero inserito se rispetta le condizioni
     * */

        public static int check() {
            Scanner in = new Scanner(System.in);
            int i = in.nextInt();
            if (i > 5 || i < 1) {
                return i;
            } else {
                System.out.println("Inserire valutazione tra 1 e 5 ");
                if (in.nextLine().equalsIgnoreCase("Y")) {
                    in();
                }
            }
            return -1;
        }
}
