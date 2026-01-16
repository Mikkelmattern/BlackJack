package Model.BlackJack;

import Model.Card;
import Model.CardType;

public class BlackJackCard extends Card {
    int value;
    int cardNumber;

    public BlackJackCard(int cardNumber, CardType type) {
        super(cardNumber, type);
        makeBlackJack();
    }

    void makeBlackJack() {
        if (cardNumber >= 10 && cardNumber != 14) {
            value = 10;
        } else if (cardNumber == 14) {
            value = 11;
        } else
            value = cardNumber;
    }

    @Override
    public void setValue(int i) {
        value = i;
    }

    @Override
    public int getValue() {
        return value;
    }
}
