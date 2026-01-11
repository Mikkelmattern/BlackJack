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
    public Player(String name){
        this.name = name;
        this.balance = Integer.MAX_VALUE;
    }

    public void startCards(List<Card> cardsToGet, int cardAmount) {
        int i = 0;
        while (cardAmount > playerCards.size()) {
            playerCards.add(cardsToGet.get(i));
            cardsToGet.remove(i);
            i++;
        }
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void addCard(List<Card> cardsToGet) {
        playerCards.add(cardsToGet.get(playerCards.size()+1));
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
