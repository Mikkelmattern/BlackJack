package Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> playerCards = new ArrayList<>();
    private int balance = 0;
    private String name;

    public Player(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }

    public void addCards(List<Card> cardsToGet, int cardAmount) {
        int i = 0;
        while (cardAmount > playerCards.size()) {
           playerCards.add(cardsToGet.get(i));
           i++;
        }
    }
    public List<Card> getPlayerCards(){
        return playerCards;
    }
}
