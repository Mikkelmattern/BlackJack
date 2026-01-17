package Logic;

import Model.*;
import Model.BlackJack.BlackJackCard;
import Model.BlackJack.BlackJackDeck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StartApp {
    BlackJackDeck deck1 = new BlackJackDeck();
    Player dealer = new Player("Dealer");
    Scanner sc = new Scanner(System.in);
    Hand dealersHand = dealer.getPlayerHand().getFirst();
    List<Player> playerList = new ArrayList<>();
    int dealerHandValue;
    int currentHandValue;
    String name;


    /**
     * Starts and runs a new blackjack game session.
     * <p>
     * Initializes the deck, registers the initial player, and drives the main
     * game loop until the user chooses to exit.
     */
    public void begin() {
        createDeckAndShuffle(deck1);
        addPlayer(100, "Mikkel");
        while (true) {
            initDealer();
            System.out.println("Dealers cards: " + dealersHand + "\n" + calculateHandValue(dealersHand));
            for (Player p : playerList) {

                List<Hand> playersCard = p.getPlayerHand();
                String name = p.getName();
                Hand currenthand = p.getPlayerHand().getFirst();
                Hand dealersHand = dealer.getPlayerHand().getFirst();
                int dealerHandValue = calculateHandValue(dealersHand);
                for (Hand h : p.getPlayerHand()) {
                    int currentHandValue = calculateHandValue(h);
                    if (isTwentyOne(dealersHand) && isTwentyOne(currenthand)) {
                        System.out.println("Push!");
                        continue;
                    } else if (isTwentyOne(dealersHand)) {
                        System.out.println("Dealer vinder!");
                        continue;
                    } else {
                        doPlayerActions(h);

                        dealerLogic();

                    }
                    if (calculateHandValue(h) > 21) {
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

    public void doPlayerActions(Hand h) {
        while (canHit(h)) {
            PlayerActions actionAnswer = promptHitStandOrSplit(h);
            if (actionAnswer == PlayerActions.HIT) {
                System.out.println(h.getCards() + "\n" + calculateHandValue(h));
            } else if (actionAnswer == PlayerActions.SPLIT && canSplit(h)) {
                //TODO
                //add doSplit method

            } else if (actionAnswer == PlayerActions.CONTINUE) {
                break;
            }
        }

    }


    public PlayerActions promptHitStandOrSplit(Hand hand) {
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
    }

    public void dealerLogic() {
        while (calculateHandValue(dealersHand) < 17) {
            doHit(dealersHand);
            System.out.println(dealersHand + "\n" + calculateHandValue(dealersHand));
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
        return calculateHandValue(hand) == 21;
    }

    public boolean canHit(Hand hand) {
        return calculateHandValue(hand) > 21;
    }

    public void doHit(Hand hand) {
        hand.addCard(hand);
    }

    public void doStand(Player player) {

    }

    public boolean canSplit(Hand hand) {
        List<Card> c = hand.getCards();
        BlackJackCard firstCard = (BlackJackCard) c.getFirst();
        BlackJackCard lastCard = (BlackJackCard) c.getLast();
        return (c.size() == 2 && firstCard.getValue() == lastCard.getValue());
    }

    public String s1(Hand h) {
        int currentHandValue = calculateHandValue(h);
        if (isTwentyOne(dealersHand) && isTwentyOne(h)) {
            return "Push";

        } else if (isTwentyOne(dealersHand)) {
            return "Dealer vinder!";
        } else {
            doPlayerActions(h);

            dealerLogic();

        }
        return s(h);
    }


    public String s(Hand h) {
        if (calculateHandValue(h) > 21) {

            return "busted!";
        } else if (dealerHandValue > 21) {
            return "Busted!";
        } else if (dealerHandValue > currentHandValue) {
            return "Dealer vinder!";
        } else if (dealerHandValue < currentHandValue) {
            return name + "vinder!";
        }


        return "";
    }

}
