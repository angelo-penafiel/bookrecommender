package bookrecommender.interfaccia.register;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import bookrecommender.elaborazione.entities.user.PasswordManager;
import bookrecommender.elaborazione.entities.utils.CSVToHashMap;

import java.util.regex.*;

/**
 * Un menù che chiede all'utente i dati e li salva in un'array, aggiornando nel mentre la HasMap che contiene
 * globalmente tutti gli utenti
 *
 * @author Leonardo Basso
 * @see bookrecommender.elaborazione.entities.user.User
 */
public class RegistrazioneMessaggi {
    /**
     * Questo metodo salva i dati di registrazione dell'utente in un array
     *
     * @return Un array che salva: {@code [0] Nome}, {@code [1] Cognome}, {@code [2] UserID}, {@code [3] Taxcode}, {@code [4] Mail}, {@code [5] Password}
     */
    public static String[] in() throws NoSuchAlgorithmException {
        Scanner in = new Scanner(System.in);
        String[] user = new String[6];

        // Init o recall dell'HashMap
        CSVToHashMap users = CSVToHashMap.getInstance();
        String[] headers = {"Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password"};
        if (!users.hasKey("UserID")) { // Controlla che l'HashMap esista o meno
            users.hashCsv("UserID", headers, "data/Database/UtentiRegistrati.csv");
        }

        // Nome
        System.out.print("Inserisci il tuo nome: ");
        user[0] = in.nextLine();

        // Cognome
        System.out.print("Inserisci il tuo cognome: ");
        user[1] = in.nextLine();

        while (true) {
            System.out.print("Inserisci il tuo UserID: ");
            String UserID = in.nextLine();
            if (users.hasKey(UserID)) {
                System.out.println("UserID già esistente, inserisci un altro UserID");
            } else {
                user[2] = UserID;
                break;
            }
        }

        // Controllo Codice Fiscale
        while (true) {
            System.out.print("Inserisci il tuo codice fiscale: ");
            String taxcode = in.nextLine().toUpperCase();
            String emailRegex = "^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(taxcode);
            if (matcher.matches()) {
                user[3] = taxcode;
                break;
            } else {
                System.out.println("Codice fiscale non valido, riprova");
            }
        }

        // Controllo Email (valid email input only)
        while (true) {
            System.out.print("Inserisci la tua mail: ");
            String email = in.nextLine();
            String emailRegex = "^\\S+@\\S+\\.[a-z]{2,}$"; // \\s -> non spazio bianco, [a-z]{2,} -> da "a" a "z" minimo 2 lettere
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                user[4] = email;
                break;
            } else {
                System.out.println("Email non valida, riprova");
            }
        }

        // Controllo Password
        while (true) {
            System.out.print("Inserisci la tua password: ");
            String pass = in.nextLine();

            System.out.print("Ripeti la tua password: ");
            String confirmPass = in.nextLine();
            if (pass.equals(confirmPass)) {
                user[5] = PasswordManager.encrypt(pass);
                break;
            } else {
                System.out.println("Le due password non corrispondono, riprova");
            }
        }

        users.add(user[2], user); // Aggiorno l'HashMap "globale"
        return user;
    }
}
