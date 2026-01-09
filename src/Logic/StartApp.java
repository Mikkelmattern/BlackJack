package Logic;

import Model.*;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StartApp {
    Deck deck1 = new Deck();
    Player player1 = new Player(1000, "Mikkel");
    ListIterator<Card> cardListIterator = player1.getPlayerCards().listIterator();

    public void givePlayerCards() {
        deck1.createBlackJackDeck();
        deck1.shuffleDeck();
        player1.addCards(deck1.getCardDeck(), 2);
    }

    public List<Card> showPlayerCards() {
        return player1.getPlayerCards();
    }

    public int sumOfCardsBlackJack() {
        int totalValue = 0;
        while (cardListIterator.hasNext()) {
            BlackJackCards card1 = (BlackJackCards) cardListIterator.next();
            totalValue += card1.getValue();
        }
        return totalValue;
    }

}
