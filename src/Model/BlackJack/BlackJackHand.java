package Model.BlackJack;

import Model.Card;
import Model.Hand;

import java.util.Iterator;
import java.util.List;

public class BlackJackHand extends Hand {
    List<BlackJackCard> cardsInHand;


    public int getValue() {
        Iterator<Card> iterator = getCards().iterator();
        int totalSum = 0;
        int aces = 0;
        while (iterator.hasNext()) {
            Card currentCard = iterator.next();
            if(!(currentCard instanceof BlackJackCard currentBlackJackCard)){
                throw new ClassCastException("Expected BlackJackCard, got " + currentCard.getClass().getSimpleName());
            }
            totalSum += currentCard.getValue();
            if (currentCard.isAce()) {
                aces++;
                while (totalSum > 21 && aces > 0) {
                    totalSum -= 10;
                    aces--;
                }
            }
            totalSum += totalSum;




        }
        return totalSum;
    }


    @Override
    public String toString() {
        return super.toString();
    }
    public void addCard(Hand cardsToGet) {
        List<Card> cards = cardsToGet.getCards();
        cardsInHand.add((BlackJackCard) cards.get(cardsInHand.size()+1));
    }
}
