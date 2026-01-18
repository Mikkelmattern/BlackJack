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
        createDeckAndShuffle();
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

    public void createDeckAndShuffle() {
        createBlackJackDeck();
        shuffleDeck();
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
    @Override
    public String toString() {
        return "" + cardDeck;
    }
}
