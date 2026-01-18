package Model;

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
    public void addCardValue(Deck cardsToGet) {

        cardsInHand.add(cards.get(cardsInHand.size()+1));
    }
    public int cardAmount(){
        int totalAmountOfCards = 0;
        for(Card c : cardsInHand){
            totalAmountOfCards++;
        }
        return totalAmountOfCards;
    }
    public Card draw() {
        return cardsInHand.removeFirst(); // eller sidste element
    }
}
