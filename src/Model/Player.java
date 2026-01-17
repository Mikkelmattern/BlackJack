package Model;

import Model.BlackJack.BlackJackCard;
import Model.BlackJack.BlackJackDeck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private final List<Hand> playerHands = new ArrayList<>();
    private int balance;
    private final String name;

    public Player(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
    public void initPlayers() {
            getPlayerHand().clear();
            givePlayerCards();
            calculateHandValue(playerHands.getFirst());
    }
    public void calculateHandValue(){

    }
    public Player(String name){
        this.name = name;
        this.balance = Integer.MAX_VALUE;
    }

    public void startCards(Deck cardsToGet, int cardAmount) {
        int i = 0;
        while (cardAmount > playerHands.size()) {
            playerHands.getFirst().getCards().add(cardsToGet.getCardDeck().get(i));
            i++;
        }
    }
    public void givePlayerCards() {
        startCards(deck, 2);
    }

    public List<Hand> getPlayerHand() {
        return playerHands;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
