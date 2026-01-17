package Model;

public class Card {
    int cardNumber;
    CardType type;

    Card(int cardNumber, CardType type) {
        this.cardNumber = cardNumber;
        this.type = type;
    }


    public int getValue() {
        return cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setValue(int i) {
    }

    public boolean isAce() {
        return cardNumber == 14;
    }


    @Override
    public String toString() {
        String numCard = "" + cardNumber;
        switch (cardNumber) {
            case 14:
                numCard = "ACE";
                break;
            case 13:
                numCard = "KING";
                break;
            case 12:
                numCard = "QUEEN";
                break;
            case 11:
                numCard = "JACK";
                break;
        }
        return numCard + type.getEmoji();
    }
}
