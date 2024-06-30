package bookrecommender.struttura.registrazione;

import java.util.Scanner;

/**
 * This class is a <u>menu</u> that saves into an array the UserID and the password in order to perform a login
 * @author Leonardo Basso
 * @see bookrecommender.elaborazione.entities.user.User
 */
public class LoginMessaggi {
    /**
     * This method records the {@code UserID} and the {@code password} of a user into an Array
     * @return the array that saves: {@code [0] UserID}, {@code [1] password}
     */
    public static String[] in() {
        String[] Login = new String[2];
        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo UserID: ");
        Login[0] = in.nextLine();

        System.out.print("Inserisci la tua password: ");
        Login[1] = in.nextLine();

        return Login;

    }
}