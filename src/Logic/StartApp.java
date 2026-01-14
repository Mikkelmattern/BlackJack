package Logic;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartApp {
    Deck deck1 = new Deck();
    Player dealer = new Player("Dealer");
    Scanner sc = new Scanner(System.in);
    List<Hand> dealerCards = dealer.getPlayerHand();
    List<Player> playerList = new ArrayList<>();

    public void begin() {
        createDeckAndShuffle(deck1);
        addPlayer(100, "Mikkel");
        while (true) {
            initDealer();
            initPlayers();
            for (Player p : playerList) {
                List<Hand> playersCard = p.getPlayerHand();
                String name = p.getName();

                int playerHandValue = calculateHandValue(playersCard);
                int dealerHandValue = calculateHandValue(dealerCards);
                if (isTwentyOne(dealer) && isTwentyOne(p)) {
                    System.out.println("Push!");
                    continue;
                } else if (isTwentyOne(dealer)) {
                    System.out.println("Dealer vinder!");
                    continue;
                } else {
                    doPlayerActions(p);

                    dealerLogic();

                }
                if (playerHandValue > 21) {
                    System.out.println(name + "busted!");
                    return;
                } else if (dealerHandValue > 21) {
                    System.out.println("Dealer busted!");
                    return;
                } else if (dealerHandValue > playerHandValue) {
                    System.out.println("Dealer vinder!");
                    return;
                } else if (dealerHandValue < playerHandValue) {
                    System.out.println(name + " vinder!");
                    return;
                }

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
        while (canHit(p)) {
            PlayerActions actionAnswer = promptHitStandOrSplit(p);
            if (actionAnswer == PlayerActions.HIT) {
                System.out.println(p.getPlayerHand() + "\n" + calculateHandValue(p.getPlayerHand()));
            } else if (actionAnswer == PlayerActions.SPLIT && canSplit(p.getPlayerHand())) {
                //TODO
                //add doSplit method
            } else if (actionAnswer == PlayerActions.CONTINUE) {
                break;
            }
        }

    }

    public void initPlayers() {
        for (Player p : playerList) {
            p.getPlayerHand().clear();
            givePlayerCards(p);
            calculateHandValue(p.getPlayerHand());
            System.out.println(p.getName() + "'s kort: " + p.getPlayerHand() + "\n" + calculateHandValue(p.getPlayerHand()));
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
            case "HIT" -> PlayerActions.HIT;
            case "SPLIT" -> PlayerActions.SPLIT;
            case "STAND" -> PlayerActions.STAND;
            case "CONTINUE" -> PlayerActions.CONTINUE;
            default -> null;
        };
    }

    public void initDealer() {
        dealer.getPlayerHand().clear();
        givePlayerCards(dealer);
        System.out.println("Dealers cards: " + dealerCards + "\n" + calculateHandValue(dealerCards));
    }

    public void dealerLogic() {
        while (calculateHandValue(dealerCards) < 17) {
            doHit(dealer);
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
        return player.getPlayerHand();
    }

    public boolean isTwentyOne(Player player) {
        return calculateHandValue(player.getPlayerHand()) == 21;
    }

    public boolean canHit(Player player) {
        return calculateHandValue(player.getPlayerHand()) < 21;
    }

    public void doHit(Player player) {
        player.addCard(deck1.getCardDeck());
    }

    public void doStand(Player player) {

    }

    public boolean isAce(Card card) {
        return card.getCardNumber() == 14;
    }

    public List<Integer> calculateHandValue(List<Hand> hands) {
        List<Integer> handValues = new ArrayList<>();
        for (Hand h : hands) {
            int sum = 0;
            int aces = 0;
            for (Card c : h.getCards()) {
                sum += c.getValue();
                if (isAce(c)) {
                    aces++;
                }
                while (sum > 21 && aces > 0) {
                    sum -= 10;
                    aces--;
                }
            } handValues.add(sum);
        }
        return handValues;
    }

    public boolean canSplit(List<Card> cards) {
        BlackJackCards firstCard = (BlackJackCards) cards.getFirst();
        BlackJackCards lastCard = (BlackJackCards) cards.getLast();
        return (cards.size() == 2 && firstCard.getValue() == lastCard.getValue());
    }

}
