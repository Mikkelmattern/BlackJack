package Logic;

import Model.*;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class StartApp {
    Deck deck1 = new Deck();
    Player player1 = new Player(1000, "Mikkel");
    Scanner sc = new Scanner(System.in);
    List<Card> playersCard = player1.getPlayerCards();

    public void begin() {
        deck1.createBlackJackDeck();
        deck1.shuffleDeck();

        for (int a = 0; a < 50; a++) {
            deck1.shuffleDeck();
            givePlayerCards();
            System.out.println(String.format("Dine kort %s%nResultat: %s", showPlayerCards(), calculateHandValue(playersCard)));
           playersCard.clear();
        }
    }

    public void givePlayerCards() {
        player1.startCards(deck1.getCardDeck(), 2);
    }

    public List<Card> showPlayerCards() {
        return playersCard;
    }

    public int sumOfCardsBlackJack() {
        ListIterator<Card> cardListIterator = playersCard.listIterator();
        int totalValue = 0;
        while (cardListIterator.hasNext()) {
            Card card1 = cardListIterator.next();
            totalValue += card1.getValue();
        }
        return totalValue;
    }

    public boolean isTwentyOne() {
        return calculateHandValue(playersCard) == 21;
    }

    public boolean canHit() {
        return sumOfCardsBlackJack() < 21;
    }

    public void hitMe() {
        int i = playersCard.size();
        checkForAces(playersCard.getLast());
        if (canHit()) {
            player1.addCard(deck1.getCardDeck());
        }
    }

    public void checkForAces(Card card) {
        if (sumOfCardsBlackJack() > 11) {
            card.setValue(1);
        } else {
            card.setValue(11);
        }
    }

    public boolean isAce(Card card) {
        return card.getCardNumber() == 14;
    }

    public int calculateHandValue(List<Card> cards){
        int sum = 0;
        int aces = 0;
        for (Card c : cards){
           sum+= c.getValue();
           if(isAce(c)){
               aces++;
           }
           while(sum > 21 && aces > 0){
               sum -= 10;
               aces--;
           }
        }
        return sum;
    }

}
