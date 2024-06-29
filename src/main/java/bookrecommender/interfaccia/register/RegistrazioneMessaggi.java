package bookrecommender.interfaccia.register;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import bookrecommender.elaborazione.entities.user.PasswordManager;
import bookrecommender.elaborazione.entities.utils.CSVUtils;

/**
 * A menu that asks user registration data and saves them into an array
 *
 * @see bookrecommender.elaborazione.entities.user.User
 * @author Leonardo Basso
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

        System.out.print("Inserisci il tuo cognome: ");
        user[1] = in.nextLine();

        String keyColumn = "UserID";
        String[] headers = {"Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password"};
        String path = "data/Database/UtentiRegistrati.csv";
        HashMap<String, String[]> loginHashMap = CSVUtils.hashCsv(keyColumn, headers, path);

        // Validate UserID
        while (true) {
            System.out.print("Inserisci il tuo UserID: ");
            String inUserID = in.nextLine();
            if (loginHashMap.containsKey(inUserID)) {
                System.out.println("UserID già esistente. Inserisci un altro UserID.");
            } else {
                user[2] = inUserID;
                break;
            }
        }

        System.out.print("Inserisci il tuo codice fiscale: ");
        user[3] = in.nextLine();

        System.out.print("Inserisci la tua mail: ");
        user[4] = in.nextLine();

        // Validate Password
        while (true) {
            System.out.print("Inserisci la tua password: ");
            String pass = in.nextLine();

            System.out.print("Ripeti la tua password: ");
            String confirmPass = in.nextLine();
            if (pass.equals(confirmPass)) {
                user[5] = PasswordManager.encrypt(pass);
                break;
            } else {
                System.out.println("Le due password non corrispondono, riprova.\n");
            }
        }
        return user;
    }
}
