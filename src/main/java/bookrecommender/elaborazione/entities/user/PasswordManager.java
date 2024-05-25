package bookrecommender.elaborazione.entities.user;

import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordManager {
    private String password;


    public PasswordManager(String password){
        this.password = password;
    }


    public String encrypt(String password) {
        return null;
    }


    public String decrypt(String cryptedPass){
        return cryptedPass;
    }
}
