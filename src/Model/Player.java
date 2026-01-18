package Model;

import Model.BlackJack.BlackJackHand;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Hand> playerHands = new ArrayList<>();
    private int balance;
    private String name = "";
    private Deck deck = new Deck() {
        /**
         *
         */
        @Override
        public void createDeck() {
            super.createDeck();
        }
    };

    /**
     * Creates a new player with an initial balance, name, and deck.
     *
     * @param balance the starting balance for the player
     * @param name    the player's display name
     * @param deck    the deck from which the player will draw cards
     */
    public Player(int balance, String name, Deck deck) {
        this.balance = balance;
        this.name = name;
        this.deck = deck;
    }

    /**
     * Creates a new player with an initial name
     *
     * @param name name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Initializes the blackjack player at the start of a round.
     * <p>
     * Deals the initial two cards to the player and evaluates
     * the value of the player's first hand according to
     * blackjack rules.
     * </p>
     *
     * <p>
     * Assumes that {@code playerHands} has been initialized
     * and contains at least one {@link Hand} instance.
     * </p>
     */
    public void initBlackJackPlayer() {
        BlackJackHand blackJackHand = new BlackJackHand();
        playerHands.add(blackJackHand);
        givePlayerCards(2);
        playerHands.getFirst().getValue();
    }

    /**
     * Creates a new player with a name and an unlimited starting balance.
     *
     * <p>
     * This constructor is typically used for testing or scenarios
     * where the player's balance should not be constrained.
     * </p>
     *
     * @param name the player's display name
     * @param deck the deck from which the player will draw cards
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.balance = Integer.MAX_VALUE;
    }

    /**
     * Deals cards to the player's first hand until the hand's value
     * reaches or exceeds the specified target value.
     *
     * <p>
     * Cards are taken sequentially from the provided deck and added
     * directly to the first hand in {@code playerHands}.
     * </p>
     * <p>
     * This method assumes that {@code playerHands} and contains at least one hand.
     * </p>
     *
     * @param cardsToGet the deck from which cards are taken
     * @param cardAmount the target hand value that determines
     *                   when dealing stops
     */
    public void startCards(Deck cardsToGet, int cardAmount) {
        int i = 0;
        playerHands.getFirst().addCardValue(cardsToGet);
        while (cardAmount > playerHands.getFirst().getCards().size()) {
            playerHands.getFirst().draw();
            i++;
        }
    }

    public void givePlayerCards(int cardAmount) {
        startCards(deck, cardAmount);
    }

    public List<Hand> getPlayerHand() {
        return playerHands;
    }

    public String getName() {
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
