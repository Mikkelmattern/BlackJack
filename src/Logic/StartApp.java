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
        createDeckAndShuffle(deck1);
        addPlayer(100, "Mikkel");
        while (true) {
            initDealer();
            initPlayers();
            for (Player p : playerList) {
                if (isTwentyOne(dealer) && isTwentyOne(p)) {
                    System.out.println("Push!");
                } else if (isTwentyOne(dealer)) {
                    System.out.println("Dealer vinder!");
                } else doPlayerActions(p);
            }
            if (sc.nextLine().matches("^1$")) {
                System.exit(0);
            }
        }
    }

    public void createDeckAndShuffle(Deck deck) {
        deck.createBlackJackDeck();
        deck.shuffleDeck();
    }

    public void doPlayerActions(Player p) {
        while (canHit(p) && promptHitStandOrSplit(p) == PlayerActions.HIT) {
            System.out.println(p.getPlayerCards() + "\n" + calculateHandValue(p.getPlayerCards()));
        }

    }

    public void initPlayers() {
        for (Player p : playerList) {
            p.getPlayerCards().clear();
            givePlayerCards(p);
            calculateHandValue(p.getPlayerCards());
            System.out.println(p.getName() + "'s kort: " + p.getPlayerCards() + "\n" + calculateHandValue(p.getPlayerCards()));
        }
    }

    public PlayerActions promptHitStandOrSplit(Player player) {
        System.out.println("Hit or Stand?");
        String scannerOut = sc.nextLine();
        while (!(scannerOut.matches("^(?i)(Hit|Stand|Split)$"))) {
            System.out.println("Choose hit or stand");
            scannerOut = sc.nextLine();
        }
        return switch (scannerOut.toUpperCase()) {
            case "HIT" -> {
                hitMe(player);
                yield PlayerActions.HIT;
            }
            case "SPLIT" -> PlayerActions.SPLIT;
            case "STAND" -> PlayerActions.STAND;
            default -> null;
        };
    }

    public void initDealer() {
        dealer.getPlayerCards().clear();
        givePlayerCards(dealer);
        System.out.println(dealerCards + "\n" + calculateHandValue(dealerCards));
    }

    public void dealerLogic() {
        while (calculateHandValue(dealerCards) < 17) {
            hitMe(dealer);
            System.out.println(dealerCards + "\n" + calculateHandValue(dealerCards));
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
