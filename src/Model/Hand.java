package Model;

import java.util.List;

public class Hand {
    List<Card> cardsInHand;
    public List<Card> getCards(){
        return cardsInHand;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public void addCard(Hand cardsToGet) {
        List<Card> cards = cardsToGet.getCards();
        cardsInHand.add(cards.get(cardsInHand.size()+1));
    }
}
