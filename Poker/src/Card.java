
/**
 *
 * @author jacob davis
 *
 * @version 1/9/20
 */
public class Card {

     enum Suit{
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

     enum Rank{
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    private Suit suit;
    private Rank rank;

    /**
     * Constructs a card
     * @param suit of card
     * @param rank of card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the value of the card
     * @return rank of card
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Gets the suit of card
     * @return suit of card
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Displays card as a rank and suit for testing
     */
    @Override
    public String toString() {
        return this.rank.toString().toLowerCase() + " of " +
    this.suit.toString().toLowerCase();
    }
}
