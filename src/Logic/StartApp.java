package Logic;

import Model.*;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class StartApp {
    Deck deck1 = new Deck();
    Player player1 = new Player(1000, "Mikkel");
    Scanner sc = new Scanner(System.in);

    public void begin() {
        deck1.createBlackJackDeck();
        deck1.shuffleDeck();

        for(int a = 0; a<50; a++){
            deck1.shuffleDeck();
            givePlayerCards();
            System.out.println(String.format("Dine kort %s%nResultat: %s", showPlayerCards(), sumOfCardsBlackJack()));
            player1.getPlayerCards().clear();
        }
    }

    public void givePlayerCards() {
        player1.startCards(deck1.getCardDeck(), 2);
    }

    public List<Card> showPlayerCards() {
        return player1.getPlayerCards();
    }

    public int sumOfCardsBlackJack() {
        ListIterator<Card> cardListIterator = player1.getPlayerCards().listIterator();
        int totalValue = 0;
        while (cardListIterator.hasNext()) {
            Card card1 = cardListIterator.next();
            totalValue += card1.getValue();
        }
        return totalValue;
    }

    public boolean isTwentyOne() {
        return sumOfCardsBlackJack() == 21;
    }

    public boolean canHit() {
        return sumOfCardsBlackJack() < 21;
    }

    public void hitMe() {
        if (canHit()) {
            player1.addCard(deck1.getCardDeck());
        }
    }
}
