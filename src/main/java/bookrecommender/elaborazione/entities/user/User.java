package bookrecommender.elaborazione.entities.user;

import java.io.*;

import bookrecommender.elaborazione.entities.utils.CSVToHashMap;
import bookrecommender.interfaccia.register.RegistrazioneMessaggi;
import bookrecommender.interfaccia.register.LoginMessaggi;

/**
 * Implementa alcuni metodi utili per la gestione dell'utente e dei suoi dati
 *
 * @author Leonardo Basso
 * @see RegistrazioneMessaggi
 * @see RegistrazioneMessaggi
 * @see LoginMessaggi
 * @see PasswordManager
 */
public class User {
    /**
     * Registra un'utente in un file {@code .csv}
     *
     * I parametri salvati sono: {@code Nome}, {@code Cognome}, {@code UserID}, {@code Taxcode}, {@code Mail}, {@code Password}
     */
    public static String register() {
        try {
            String[] user = RegistrazioneMessaggi.in();
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Database/UtentiRegistrati.csv", true));
            if (user != null) {
                writer.write(user[0] + "," + user[1] + "," + user[2] + "," + user[3] + "," + user[4] + "," + user[5] + "\n");
                writer.close();
                return user[2];
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Consente a un utente di accedere all'app usando:
     * <a href="https://commons.apache.org/proper/commons-csv/">common-csv</a> by Apache
     *
     * @return String[] con l'{@code UserID} a [0] e la {@code Password} a [1]
     */
    public static String login() {
        try {
            // Init o recall dell'HashMap
            CSVToHashMap users = CSVToHashMap.getInstance();
            String[] headers = {"Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password"};
            if (!users.hasKey("UserID")) {
                users.hashCsv("UserID", headers, "data/Database/UtentiRegistrati.csv");
            }

            String[] LoginData = LoginMessaggi.in();
            String[] userData = users.getValues(LoginData[0]);

            if (userData != null && PasswordManager.compare(LoginData[1], userData[5])) {
                System.out.println("Login SUCCESS!");
                return LoginData[0]; // Ritorna il UserID dell'utente loggato
            } else {
                System.out.println("UserID o Password non validi.");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
