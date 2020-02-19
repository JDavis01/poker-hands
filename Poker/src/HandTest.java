import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jacob davis
 *
 * @version 1/9/20
 */
public class HandTest {
    Hand hand;
    Card kingD;
    Card jackC;
    Card sevenH;
    Card twoD;
    Card queenS;

    Hand hand2;

    Hand pairHand;
    Card queenH;

    Hand twoPairHand;
    Card twoS;

    Hand twoPairHand2;
    Card sevenD;

    Hand twoPairHand3;

    Hand threeKindHand;
    Card queenC;

    Hand straight;
    Card threeC;
    Card fourD;
    Card fiveS;
    Card sixC;

    Hand straight2;
    Card aceS;
    Card tenH;

    Hand straight3;

    Hand flush;
    Card jackD;

    Hand fullHouse;

    Hand fullHouse2;
    Card kingC;

    Hand fourKind;
    Card queenD;

    Hand fourKind2;

    Hand straightFlush;
    Card threeD;
    Card fiveD;
    Card sixD;

    Hand straightFlush2;
    Card tenS;
    Card jackS;
    Card kingS;

    Hand pairHand2;

    Hand pairHand3;

    Hand twoPairHand4;

    Hand trips;
    Card twoH;

    Hand flush2;

    Card twoC;

    /**
     * Sets up cards and hands for tests
     */
    @Before
    public void setUp() {
        kingD = new Card(Card.Suit.DIAMONDS, Card.Rank.KING);
        jackC = new Card(Card.Suit.CLUBS, Card.Rank.JACK);
        sevenH = new Card(Card.Suit.HEARTS, Card.Rank.SEVEN);
        twoD = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        queenS = new Card(Card.Suit.SPADES, Card.Rank.QUEEN);
        hand = new Hand(kingD, jackC, sevenH, twoD, queenS);

        queenH = new Card(Card.Suit.HEARTS, Card.Rank.QUEEN);
        pairHand = new Hand(kingD, queenH, sevenH, twoD, queenS);

        twoS = new Card(Card.Suit.SPADES, Card.Rank.TWO);
        twoPairHand = new Hand(twoS, queenH, sevenH, twoD, queenS);

        sevenD = new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN);
        twoPairHand2 = new Hand(twoS, queenH, sevenH, sevenD, queenS);

        twoPairHand3 = new Hand(twoS, twoD, sevenH, sevenD, queenS);

        queenC = new Card(Card.Suit.CLUBS, Card.Rank.QUEEN);
        threeKindHand = new Hand(queenC, queenH, twoD, sevenD, queenS);

        threeC = new Card(Card.Suit.CLUBS, Card.Rank.THREE);
        fourD = new Card(Card.Suit.DIAMONDS, Card.Rank.FOUR);
        fiveS = new Card(Card.Suit.SPADES, Card.Rank.FIVE);
        sixC = new Card(Card.Suit.CLUBS, Card.Rank.SIX);
        straight = new Hand(twoD, threeC, fourD, fiveS, sixC);

        aceS = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        tenH = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        straight2 = new Hand(tenH, jackC, queenS, kingD, aceS);

        straight3 = new Hand(twoD, threeC, fourD, fiveS, aceS);

        jackD = new Card(Card.Suit.DIAMONDS, Card.Rank.JACK);
        flush = new Hand(twoD, jackD, fourD, kingD, sevenD);

        fullHouse = new Hand(queenC, queenH, sevenH, sevenD, queenS);

        kingC = new Card(Card.Suit.CLUBS, Card.Rank.KING);

        queenD = new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN);
        fourKind = new Hand(queenC, queenH, twoD, queenD, queenS);

        threeD = new Card(Card.Suit.DIAMONDS, Card.Rank.THREE);
        fiveD = new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE);
        sixD = new Card(Card.Suit.DIAMONDS, Card.Rank.SIX);
        straightFlush = new Hand(twoD, threeD, fourD, fiveD, sixD);

        tenS = new Card(Card.Suit.SPADES, Card.Rank.TEN);
        jackS = new Card(Card.Suit.SPADES, Card.Rank.JACK);
        kingS = new Card(Card.Suit.SPADES, Card.Rank.KING);
        straightFlush2 = new Hand(tenS, jackS, queenS, kingS, aceS);

        hand2 = new Hand(threeC, jackC, sevenH, twoD, queenS);

        pairHand2 = new Hand(jackC, queenC, sevenD, threeD, queenH);

        pairHand3 = new Hand(jackC, jackS, tenS, threeD, queenH);

        twoPairHand4 = new Hand(twoS, queenH, jackC, twoD, queenS);

        twoH = new Card(Card.Suit.HEARTS, Card.Rank.TWO);
        trips = new Hand(twoS, twoH, jackC, twoD, queenS);

        flush2 = new Hand(twoS, jackS, tenS, kingS, aceS);

        fullHouse2 = new Hand(twoD, kingS, kingC, kingD, twoS);

        twoC = new Card(Card.Suit.CLUBS, Card.Rank.TWO);
        fourKind2 = new Hand(twoD, twoS, kingC, twoC, twoH);
    }


    /**
     * Tests if a card is made correctly
     */
    @Test
    public void testCard() {
        Card twoD = new Card(Card.Suit.DIAMONDS, Card.Rank.TWO);
        assertEquals(Card.Suit.DIAMONDS, twoD.getSuit());
        assertEquals(Card.Rank.TWO, twoD.getRank());
    }

    /**
     * Tests if hand is sorted in ascending order
     */
    @Test
    public void testSortedHand() {
        assertEquals(Card.Rank.TWO, hand.getCard(0).getRank());
        assertEquals(Card.Rank.SEVEN, hand.getCard(1).getRank());
        assertEquals(Card.Rank.JACK, hand.getCard(2).getRank());
        assertEquals(Card.Rank.QUEEN, hand.getCard(3).getRank());
        assertEquals(Card.Rank.KING, hand.getCard(4).getRank());
    }

    /**
     * Tests if has pair works correctly
     */
    @Test
    public void testPair() {
        assertFalse(hand.hasPair());
        assertTrue(pairHand.hasPair());
    }

    /**
     * Tests if has 2 pair works correctly
     */
    @Test
    public void test2Pair() {
        assertFalse(pairHand.has2Pair());
        assertTrue(twoPairHand.has2Pair());
        assertTrue(twoPairHand2.has2Pair());
        assertTrue(twoPairHand3.has2Pair());
    }

    /**
     * Tests if has 3 of a kind works correctly
     */
    @Test
    public void test3OfAKind() {
        assertFalse(twoPairHand.has3OfKind());
        assertTrue(threeKindHand.has3OfKind());
    }

    /**
     * Tests if has straight works correctly
     */
    @Test
    public void testStraight() {
        assertFalse(threeKindHand.hasStraight());
        assertTrue(straight.hasStraight());
        assertTrue(straight2.hasStraight());
        assertTrue(straight3.hasStraight());
    }

    /**
     * Tests if has flush works correctly
     */
    @Test
    public void testFlush() {
        assertFalse(straight.hasFlush());
        assertTrue(flush.hasFlush());
    }

    @Test
    public void testFullHouse() {
        assertFalse(flush.hasFullHouse());
        assertTrue(fullHouse.hasFullHouse());
        assertTrue(fullHouse2.hasFullHouse());
    }

    /**
     * Tests if has 4 of a kind works correctly
     */
    @Test
    public void test4OfKind() {
        assertFalse(threeKindHand.has4OfKind());
        assertTrue(fourKind.has4OfKind());
        assertTrue(fourKind2.has4OfKind());
    }

    /**
     * Tests if has straight flush works correctly
     */
    @Test
    public void testStraightFlush() {
        assertFalse(straight.hasStraightFlush());
        assertFalse(flush.hasStraightFlush());
        assertTrue(straightFlush.hasStraightFlush());
        assertTrue(straightFlush2.hasStraightFlush());
    }

    /**
     * Tests display winner in many cases of hand possibilites and tie breaker cases
     */
    @Test
    public void testDisplayWinner() {
        assertEquals("Player1 wins!: with a straight flush.\n"
                + "[two of diamonds, three of diamonds, four of diamonds,"
                + " five of diamonds, six of diamonds]",
                Hand.displayWinner(straightFlush, flush));
        assertEquals("Player2 wins!: with a straight flush.\n"
                + "[two of diamonds, three of diamonds, four of diamonds,"
                + " five of diamonds, six of diamonds]",
                Hand.displayWinner(flush, straightFlush));

        assertEquals("Player1 wins!: with a high card of king of diamonds",
                Hand.displayWinner(hand, hand2));

        assertEquals("Player1 wins!: with a pair and a high card of king of diamonds.",
                Hand.displayWinner(pairHand, pairHand2));

        assertEquals("Player2 wins!: with a pair of queens.",
                Hand.displayWinner(pairHand3, pairHand2));

        assertEquals("Player2 wins!: with a higher pair of queens.",
                Hand.displayWinner(twoPairHand3, twoPairHand));

        assertEquals("Player1 wins!: with a higher lower pair of sevens.",
                Hand.displayWinner(twoPairHand2, twoPairHand));

        assertEquals("Player1 wins!: with a higher kicker of jack of clubs.",
                Hand.displayWinner(twoPairHand4, twoPairHand));

        assertEquals("Player2 wins!: with higher trips of queens.",
                Hand.displayWinner(trips, threeKindHand));

        assertEquals("Player2 wins!: with higher straight up to ace.",
                Hand.displayWinner(straight, straight2));

        assertEquals("Player2 wins!: with a royal straight.",
                Hand.displayWinner(straight3, straight2));

        assertEquals("Player1 wins!: with a flush.\n" +
                "[two of diamonds, four of diamonds, "
                + "seven of diamonds, jack of diamonds, king of diamonds]",
                Hand.displayWinner(flush, straight2));

        assertEquals("Player2 wins!: with a high card of ace of spades with flush.",
                Hand.displayWinner(flush, flush2));

        assertEquals("Player1 wins!: with a full house.\n"
                + "[two of diamonds, two of spades, king of spades,"
                + " king of clubs, king of diamonds]",
                Hand.displayWinner(fullHouse2, flush));

        assertEquals("Player2 wins!: with higher trips of kings.",
                Hand.displayWinner(fullHouse, fullHouse2));

        assertEquals("Player1 wins!: with a four of a kind.\n"
                + "[two of diamonds, two of spades, two of clubs,"
                + " two of hearts, king of clubs]",
                Hand.displayWinner(fourKind2, fullHouse2));

        assertEquals("Player2 wins!: with higher four of a kind of queens.",
                Hand.displayWinner(fourKind2, fourKind));

        assertEquals("Player1 wins!: with a straight flush.\n" +
                "[two of diamonds, three of diamonds, "
                + "four of diamonds, five of diamonds, six of diamonds]",
                Hand.displayWinner(straightFlush, fourKind));

        assertEquals("Player1 wins!: with a high card of ace of spades "
                + "with straight flush.",
                Hand.displayWinner(straightFlush2, straightFlush));

        assertEquals("Tie.",
                Hand.displayWinner(straightFlush2, straightFlush2));
    }
}

