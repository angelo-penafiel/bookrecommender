package bookrecommender.struttura;

import bookrecommender.struttura.menu.MenuIniziale;
import bookrecommender.struttura.menu.MenuPrincipale;
import bookrecommender.elaborazione.entities.user.User;

public class BookRecommender {

    public static void main(String[] args) {

        boolean controllo;

        controllo = true;
        controllo = menuIniziale(controllo);
    }

    private static boolean menuIniziale(boolean controllo) {

        var menuIniziale = new MenuIniziale();

        if (menuIniziale.getScelta() == 1) {
            User.login();
        }

        if (menuIniziale.getScelta() == 2) {
            User.register();
        }

        if (menuIniziale.getScelta() == 3) {

            var menuPrincipale = new MenuPrincipale(0);
        }
        return controllo;
    }
}
