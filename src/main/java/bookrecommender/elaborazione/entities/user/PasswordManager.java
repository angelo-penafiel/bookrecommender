package bookrecommender.elaborazione.entities.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Questa classe gestisce la gestione delle password e la crittografia
 * @author Leonardo Basso
 */
public class PasswordManager {
    /**
     * Questo metodo esegue l'hashing della password utilizzando {@code SHA-256} come algoritmo
     * @param password La password senza hash
     * @return la password con hash come stringa
     */
    public static String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] passDigest = md.digest(password.getBytes());
        BigInteger boh = new BigInteger(1, passDigest);
        return boh.toString(16);
    }

    /**
     * Questo metodo confronta due password
     * @param inputPass  La password che non è sottoposta a hashing
     * @param hashedPass La password con hash
     * @return {@code true} se le due password corrispondono
     */
    public static boolean compare(String inputPass, String hashedPass) throws NoSuchAlgorithmException {
        return encrypt(inputPass).equals(hashedPass);
    }
}