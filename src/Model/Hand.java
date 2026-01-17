package Model;

import Model.BlackJack.BlackJackCard;

import java.util.Iterator;
import java.util.List;

public abstract class Hand {
    List<Card> cardsInHand;
    public List<Card> getCards(){
        return cardsInHand;
    }

    public abstract int getValue();


    @Override
    public String toString() {
        return super.toString();
    }
    public void addCard(Hand cardsToGet) {
        List<Card> cards = cardsToGet.getCards();
        cardsInHand.add(cards.get(cardsInHand.size()+1));
    }
}
