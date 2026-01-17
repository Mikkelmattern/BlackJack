package Model;

import Model.BlackJack.BlackJackCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
    List<Card> cardDeck = new ArrayList<>();

    public void createDeck() {
        for (CardType card : CardType.values()) {
            int i = 1;

            while (i < 15) {
                cardDeck.add(new BlackJackCard(i, card));
                i++;
            }
        }
    }

    public void createBlackJackDeck() {
        for (CardType card : CardType.values()) {
            int i = 1;

            while (i < 15) {
                cardDeck.add(new BlackJackCard(i, card));
                i++;
            }
        }
        for (Card c : cardDeck) {
            if (c instanceof BlackJackCard bj) {
                bj.makeBlackJack();
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
