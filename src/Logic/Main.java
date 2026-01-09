package Logic;

import Model.Deck;
import Model.Player;

public class Main {
    public static void main(String[] args) {
        StartApp sa = new StartApp();
        sa.givePlayerCards();
        System.out.println(sa.showPlayerCards());
    }
}
