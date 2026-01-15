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

    /**
     * Initializes and runs a new game session.
     *
     * Creates a new deck, registers the initial player, and runs the main game loop
     * until the user exits.
     */
/**
     * Starts and runs a new blackjack game session.
     *
     * Initializes the deck, registers the initial player, and drives the main
     * game loop until the user chooses to exit.
     */
      public void begin() {
        createDeckAndShuffle(deck1);
        addPlayer(100, "Mikkel");
        while (true) {
            initDealer();
            initPlayers();
            for (Player p : playerList) {

                List<Hand> playersCard = p.getPlayerHand();
                String name = p.getName();
                Hand currenthand = p.getPlayerHand().getFirst();
                Hand dealersHand = dealer.getPlayerHand().getFirst();
                int dealerHandValue = calculateHandValue(dealersHand.getCards());
                for (Hand h : p.getPlayerHand()) {
                    int currentHandValue = calculateHandValue(h.getCards());
                    if (isTwentyOne(dealersHand) && isTwentyOne(currenthand)) {
                        System.out.println("Push!");
                        continue;
                    } else if (isTwentyOne(dealersHand)) {
                        System.out.println("Dealer vinder!");
                        continue;
                    } else {
                        doPlayerActions(p);

                        dealerLogic();

                    }
                    if (calculateHandValue(h.getCards()) > 21) {
                        System.out.println(name + "busted!");
                        return;
                    } else if (dealerHandValue > 21) {
                        System.out.println("Dealer busted!");
                        return;
                    } else if (dealerHandValue > currentHandValue) {
                        System.out.println("Dealer vinder!");
                        return;
                    } else if (dealerHandValue < currentHandValue) {
                        System.out.println(name + " vinder!");
                        return;
                    }

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
                System.out.println(p.getPlayerHand() + "\n" + calculateHandValue(p.getPlayerHand().));
            } else if (actionAnswer == PlayerActions.SPLIT && canSplit(p.getPlayerHand().getFirst().getCards())) {
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
            calculateHandValue(p.getPlayerHand().getFirst().getCards());
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
        System.out.println("Dealers cards: " + dealerCards + "\n" + calculateHandValue(dealerCards.getFirst().getCards()));
    }

    public void dealerLogic() {
        while (calculateHandValue(dealerCards.getFirst().getCards()) < 17) {
            doHit(dealer);
            System.out.println(dealerCards + "\n" + calculateHandValue(dealerCards.getFirst().getCards()));
        }
    }

    public void addPlayer(int startValue, String name) {
        playerList.add(new Player(startValue, name));
    }

    public void givePlayerCards(Player player) {
        player.startCards(deck1, 2);
    }

    public List<Card> showPlayerCards(Player player) {
        return player.getPlayerHand().getFirst().getCards();
    }

    public boolean isTwentyOne(Hand hand) {
        return calculateHandValue(hand.getCards()) == 21;
    }

    public boolean canHit(Player player) {
        return calculateHandValue(player.getPlayerHand().getFirst().getCards()) < 21;
    }

    public void doHit(Player player) {
        player.addCard(deck1.getCardDeck().getFirst());
    }

    public void doStand(Player player) {

    }

    public boolean isAce(Card card) {
        return card.getCardNumber() == 14;
    }

    public int calculateHandValue(List<Card> cards) {
        for (Card c : cards) {
            int sum = 0;
            int aces = 0;

            sum += c.getValue();
            if (isAce(c)) {
                aces++;
                while (sum > 21 && aces > 0) {
                    sum -= 10;
                    aces--;
                }
            }
            return sum;
        }
        return handValues;
    }

    public boolean canSplit(List<Card> cards) {
        BlackJackCards firstCard = (BlackJackCards) cards.getFirst();
        BlackJackCards lastCard = (BlackJackCards) cards.getLast();
        return (cards.size() == 2 && firstCard.getValue() == lastCard.getValue());
    }

}
