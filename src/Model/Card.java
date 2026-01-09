package Model;

public class Card {
    int cardNumber;
    CardType type;
    Card(int cardNumber, CardType type){
        this.cardNumber = cardNumber;
        this.type = type;
    }


    @Override
    public String toString() {
        return cardNumber+type.getEmoji();
    }
}
