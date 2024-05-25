package bookrecommender.interfaccia.register;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import bookrecommender.elaborazione.entities.user.PasswordManager;

/**
 * A menu that asks user registration data and saves them into an array
 *
 * @author Leonardo Basso
 * @see bookrecommender.elaborazione.entities.user.User
 */
public class RegistrazioneMessaggi {
    /**
     * This method saves user registration data and saves them into an array
     *
     * @return An array that saves: {@code [0] Nome}, {@code [1] Cognome}, {@code [2] UserID}, {@code [3] Taxcode}, {@code [4] Mail}, {@code [5] Password}
     */
    public static String[] in() throws NoSuchAlgorithmException {
        Scanner in = new Scanner(System.in);
        String[] user = new String[6];

        System.out.print("Inserisci il tuo nome: ");
        user[0] = in.nextLine();

        System.out.print("Inserisci il tuo conome: ");
        user[1] = in.nextLine();
        System.out.print("Inserisci il tuo UserID: ");
        user[2] = in.nextLine();

        System.out.print("Inserisci il tuo codice fiscale: ");
        user[3] = in.nextLine();

        System.out.print("Inserisci la tua mail: ");
        user[4] = in.nextLine();

        System.out.print("Inserisci la tua password: ");
        String pass = in.nextLine();

        System.out.print("Ripeti la tua password: ");
        if (in.nextLine().equals(pass)) {
            user[5] = PasswordManager.encrypt(pass);
            return user;
        } else {
            System.out.println("Le due password non corrispondono, vuoi riprovare (Y/N)? ");
            if (in.nextLine().equalsIgnoreCase("Y")) {
                in();
            }
        }
        return null;
    }
}
