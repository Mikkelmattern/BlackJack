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

    public void startCards(List<Card> cardsToGet, int cardAmount) {
        int i = 0;
        while (cardAmount > playerCards.size()) {
           playerCards.add(cardsToGet.get(i));
           i++;
        }
        initSpecialAce();
    }
    public List<Card> getPlayerCards(){
        return playerCards;
    }
    public void addCard(List<Card> cardsToGet){
        playerCards.add(cardsToGet.get(playerCards.size()+1));
    }
    public void initSpecialAce(){
        Card a = playerCards.getFirst();
        if(a.getCardNumber() == 14){
            if(a instanceof BlackJackCards){
               ((BlackJackCards) a).setValue(11);
            }
        }
    }
}
