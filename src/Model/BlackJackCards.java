package Model;

public class BlackJackCards extends Card {
    int value;

    BlackJackCards(int cardNumber, CardType type) {
        super(cardNumber, type);
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
