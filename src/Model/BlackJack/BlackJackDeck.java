package Model.BlackJack;

import Model.Card;
import Model.CardType;
import Model.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDeck extends Deck {
    List<Card> cardDeck = new ArrayList<>();

    public BlackJackDeck() {
        createDeck();
    }

    public void createDeck() {
        for (CardType card : CardType.values()) {
            int i = 1;

            while (i <= 14) {
                cardDeck.add(new BlackJackCard(i, card));
                i++;
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cardDeck);
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    @Override
    public String toString() {
        return "" + cardDeck;
    }
}
