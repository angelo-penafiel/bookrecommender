package bookrecommender.elaborazione.entities.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles with password management and cryptography
 * @author Leonardo Basso
 */
public class PasswordManager {
    /**
     * This method hashes the password using {@code SHA-256} as algorithm
     * @param password The un-hashed password
     * @return The hashed password as a String
     */
    public static String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] passDigest = md.digest(password.getBytes());
        BigInteger boh = new BigInteger(1, passDigest);
        return boh.toString(16);
    }

    /**
     * This method compares two passwords
     * @param inputPass  The password that is not hashed
     * @param hashedPass The password that is hashed
     * @return {@code true} if the two password matches
     */
    public static boolean compare(String inputPass, String hashedPass) throws NoSuchAlgorithmException {
        return encrypt(inputPass).equals(hashedPass);
    }
}