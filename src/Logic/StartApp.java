package Logic;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartApp {
    Deck deck1 = new Deck();
    Player dealer = new Player("Dealer");
    Scanner sc = new Scanner(System.in);
    List<Card> dealerCards = dealer.getPlayerCards();
    List<Player> playerList = new ArrayList<>();

    public void begin() {
        deck1.createBlackJackDeck();
        addPlayer(100, "Mikkel");
    }


    public void initPlayers() {
        for (Player p : playerList) {
            givePlayerCards(p);
            calculateHandValue(p.getPlayerCards());
            System.out.println(p.getPlayerCards() + "\n" + calculateHandValue(p.getPlayerCards()));
        }
    }

    public void initDealer() {
        givePlayerCards(dealer);
        System.out.println(dealerCards + "\n" + calculateHandValue(dealerCards));
    }

    public void dealerLogic() {
        initDealer();
        if (calculateHandValue(dealerCards) < 17) {

        }
    }

    public void addPlayer(int startValue, String name) {
        playerList.add(new Player(startValue, name));
    }

    public void givePlayerCards(Player player) {
        player.startCards(deck1.getCardDeck(), 2);
    }

    public List<Card> showPlayerCards(Player player) {
        return player.getPlayerCards();
    }

    public boolean isTwentyOne(Player player) {
        return calculateHandValue(player.getPlayerCards()) == 21;
    }

    public boolean canHit(Player player) {
        return calculateHandValue(player.getPlayerCards()) < 21;
    }

    public void hitMe(Player player) {
        player.addCard(deck1.getCardDeck());
    }

    public boolean isAce(Card card) {
        return card.getCardNumber() == 14;
    }

    public int calculateHandValue(List<Card> cards) {
        int sum = 0;
        int aces = 0;
        for (Card c : cards) {
            sum += c.getValue();
            if (isAce(c)) {
                aces++;
            }
            while (sum > 21 && aces > 0) {
                sum -= 10;
                aces--;
            }
        }
        return sum;
    }

}
