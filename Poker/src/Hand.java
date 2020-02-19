import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jacob davis
 *
 * @version 1/9/20
 */
public class Hand {
    private List<Card> cards;
    private int handScore;
    private String[] handType;

    /**
     * Constructs a hand consisting of five cards
     * @param one
     * @param two
     * @param three
     * @param four
     * @param five
     */
    public Hand(Card one, Card two, Card three, Card four, Card five) {
        cards = new ArrayList<Card>();
        handScore = 0;
        handType = new String[] { "high card", "pair", "two pairs", "three of a kind", "straight", "flush",
                "full house", "four of a kind", "straight flush" };

        cards.add(one);
        cards.add(two);
        cards.add(three);
        cards.add(four);
        cards.add(five);

        Collections.sort(cards, new CardComparator<Card>());
        handScore = this.calcHandScore();
    }

    /**
     * Gives a score to hand based on how good it is
     * @return hand as a numeric score
     */
    private int calcHandScore() {
        if (this.hasStraightFlush()) {
            return 8;
        } else if (this.has4OfKind()) {
            return 7;
        } else if (this.hasFullHouse()) {
            return 6;
        } else if (this.hasFlush()) {
            return 5;
        } else if (this.hasStraight()) {
            return 4;
        } else if (this.has3OfKind()) {
            return 3;
        } else if (this.has2Pair()) {
            return 2;
        } else if (this.hasPair()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Gets a card at the position in the list.
     * Used for testing if hand was sorted
     * @param pos location in cards list
     * @return card at position in list
     */
    public Card getCard(int pos) {
        return cards.get(pos);
    }

    /**
     * Checks to see if pair exists in hand
     * @return true if pair
     */
    public boolean hasPair() {
        for (int i = 0; i < 4; i++) {
            Card.Rank match1 = cards.get(i).getRank();
            Card.Rank match2 = cards.get(i + 1).getRank();
            if (match1 == match2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if two pair exists in hand
     * @return true if two pair
     */
    public boolean has2Pair() {
        int counter = 0;

        for (int i = 0; i < 4; i++) {
            Card.Rank match1 = cards.get(i).getRank();
            Card.Rank match2 = cards.get(i + 1).getRank();
            if (match1 == match2) {
                counter++;
                i++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a three of a kind in hand
     * @return true if exists
     */
    public boolean has3OfKind() {
        for (int i = 0; i < 3; i++) {
            Card.Rank match1 = cards.get(i).getRank();
            Card.Rank match2 = cards.get(i + 1).getRank();
            Card.Rank match3 = cards.get(i + 2).getRank();
            if (match1 == match2 && match1 == match3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if straight in hand
     * @return true if straight
     */
    public boolean hasStraight() {
        int rankVal = cards.get(0).getRank().ordinal();
        int rankVal2 = cards.get(4).getRank().ordinal();

        if (this.hasPair() || this.has2Pair() || this.has3OfKind()) {
            return false;
        }

        if (rankVal2 - rankVal == 4) {
            return true;
        }

        // Ace wraps around so checks for ace through five
        else if (rankVal2 == 12 && rankVal == 0) {
            int rankVal3 = cards.get(3).getRank().ordinal();
            int rankVal4 = cards.get(1).getRank().ordinal();
            if (rankVal3 - rankVal4 == 2) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Checks for flush
     * @return true if flush exists
     */
    public boolean hasFlush() {
        int suitVal = cards.get(0).getSuit().ordinal();

        for (int i = 1; i < 4; i++) {
            int suitVal2 = cards.get(i).getSuit().ordinal();
            if (suitVal != suitVal2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for full house
     * @return true if full house
     */
    public boolean hasFullHouse() {
        int match = this.cards.get(0).getRank().ordinal();
        int match2 = this.cards.get(1).getRank().ordinal();
        int match3 = this.cards.get(2).getRank().ordinal();
        int match4 = this.cards.get(3).getRank().ordinal();
        int match5 = this.cards.get(4).getRank().ordinal();

        if (match == match2 && match3 == match5 ||
                match == match3 && match4 == match5) {
            return true;
        }
        return false;
    }

    /**
     * Checks for four of a kind
     * @return true if four of a kind
     */
    public boolean has4OfKind() {
        for (int i = 0; i < 2; i++) {
            Card.Rank match1 = cards.get(i).getRank();
            Card.Rank match2 = cards.get(i + 1).getRank();
            Card.Rank match3 = cards.get(i + 2).getRank();
            Card.Rank match4 = cards.get(i + 3).getRank();
            if (match1 == match2 && match2 == match3 && match3 == match4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for straight flush
     * @return true if straight flush
     */
    public boolean hasStraightFlush() {
        if (this.hasStraight() && this.hasFlush()) {
            return true;
        }
        return false;
    }

    // Helper method returns message to display winner and their hand
    private static String winnerMessage(Hand player, int winner) {
        String message = String.format("Player%s wins!: " + "with a %s.\n%s", winner,
                player.handType[player.handScore], player.cards.toString());
        return message;
    }

    // Helper method to find position of pair
    private static int getPairPos(Hand player, int pos) {
        for (int i = pos; i < 4; i++) {
            Card.Rank match1 = player.cards.get(i).getRank();
            Card.Rank match2 = player.cards.get(i + 1).getRank();
            if (match1 == match2) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    // Helper method for finding the next best card after a tie
    private static String getNextBestCard(Hand player1, Hand player2) {
        String message = "Tie.";
        for (int i = 4; i >= 0; i--) {
                int highCard = player1.cards.get(i).getRank().ordinal();
                int highCard2 = player2.cards.get(i).getRank().ordinal();
                String card = player1.cards.get(i).getRank().toString().toLowerCase() + " of "
                        + player1.cards.get(i).getSuit().toString().toLowerCase();
                String card2 = player2.cards.get(i).getRank().toString().toLowerCase() + " of "
                        + player2.cards.get(i).getSuit().toString().toLowerCase();
                if (highCard == highCard2) {
                    continue;
                } else if (highCard > highCard2) {
                    message = String.format("Player1 wins!: with a high card of %s", card);
                    break;
                } else if (highCard < highCard2) {
                    message = String.format("Player2 wins!: with a high card of %s", card2);
                    break;
                }
            }
        return message;
    }

    // Helper method for comparing best three of a kind
    private static String getBestTrips(Hand player1, Hand player2) {
        String message = "";
        int tripCard = player1.cards.get(2).getRank().ordinal();
        int tripCard2 = player2.cards.get(2).getRank().ordinal();
        String card = player1.cards.get(2).getRank().toString().toLowerCase();
        String card2 = player2.cards.get(2).getRank().toString().toLowerCase();

        if (tripCard > tripCard2) {
            message = String.format("Player1 wins!: with higher trips of %ss.", card);
            return message;
        }
        else {
            message = String.format("Player2 wins!: with higher trips of %ss.", card2);
            return message;
        }
    }

    // Determines who is the winner in case of a tie
    private static String tieBreaker(Hand player1, Hand player2) {
        int hand = player1.handScore;
        String message = "Tie";
        switch (hand) {
        case 0:
            message = getNextBestCard(player1, player2);
            break;
        case 1:
            int pos1 = getPairPos(player1, 0);
            int pos2 = getPairPos(player2, 0);
            int pairCard = player1.cards.get(pos1).getRank().ordinal();
            int pairCard2 = player2.cards.get(pos2).getRank().ordinal();
            int[] remainingCards = new int[5];
            int[] remainingCards2 = new int[5];
            for (int i = 0; i < 5; i++) {
                if (pairCard == player1.cards.get(i).getRank().ordinal()) {
                    remainingCards[i] = -1;
                } else {
                    remainingCards[i] = player1.cards.get(i).getRank().ordinal();
                }
            }
            for (int i = 0; i < 5; i++) {
                if (pairCard2 == player2.cards.get(i).getRank().ordinal()) {
                    remainingCards2[i] = -1;
                } else {
                    remainingCards2[i] = player2.cards.get(i).getRank().ordinal();
                }
            }

            if (pairCard > pairCard2) {
                String card = player1.cards.get(pos1).getRank().toString().toLowerCase();
                message = String.format("Player1 wins!: with a pair of %ss.", card);
            } else if (pairCard < pairCard2){
                String card = player2.cards.get(pos2).getRank().toString().toLowerCase();
                message = String.format("Player2 wins!: with a pair of %ss.", card);
            }

            else {

                for (int i = 4; i >= 0; i--) {
                    int highCard = remainingCards[i];
                    int highCard2 = remainingCards2[i];
                    String card = player1.cards.get(i).getRank().toString().toLowerCase() + " of "
                            + player1.cards.get(i).getSuit().toString().toLowerCase();
                    String card2 = player2.cards.get(i).getRank().toString().toLowerCase() + " of "
                            + player2.cards.get(i).getSuit().toString().toLowerCase();
                    if (highCard == highCard2) {
                        continue;
                    } else if (highCard > highCard2) {
                        message = String.format("Player1 wins!: with a pair and a high card of %s.", card);
                        break;
                    } else if (highCard < highCard2) {
                        message = String.format("Player2 wins!: with a pair and a high card of %s.", card2);
                        break;
                    }
                }
            }
            break;
        case 2:
            int p1Pos1 = getPairPos(player1, 0);
            int p1Pos2 = getPairPos(player1, p1Pos1 + 2);
            int p2Pos1 = getPairPos(player2, 0);
            int p2Pos2 = getPairPos(player2, p2Pos1 + 2);
            int p1Pair1 = player1.cards.get(p1Pos1).getRank().ordinal();
            int p1Pair2 = player2.cards.get(p1Pos2).getRank().ordinal();
            int p2Pair1 = player1.cards.get(p2Pos1).getRank().ordinal();
            int p2Pair2 = player2.cards.get(p2Pos2).getRank().ordinal();
            int[] p1Remaining = new int[5];
            int[] p2Remaining = new int[5];
            for (int i = 0; i < 5; i++) {
                if (p1Pair1 == player1.cards.get(i).getRank().ordinal()
                        || p1Pair2 == player1.cards.get(i).getRank().ordinal()) {
                    p1Remaining[i] = -1;
                } else {
                    p1Remaining[i] = player1.cards.get(i).getRank().ordinal();
                }
            }
            for (int i = 0; i < 5; i++) {
                if (p2Pair1 == player2.cards.get(i).getRank().ordinal()
                        || p2Pair2 == player2.cards.get(i).getRank().ordinal()) {
                    p2Remaining[i] = -1;
                } else {
                    p2Remaining[i] = player2.cards.get(i).getRank().ordinal();
                }
            }
            if (p1Pair2 > p2Pair2) {
                String card = player1.cards.get(p1Pos2).getRank().toString().toLowerCase();
                message = String.format("Player1 wins!: with a higher pair of %ss.", card);
            } else if (p1Pair2 < p2Pair2) {
                String card = player2.cards.get(p2Pos2).getRank().toString().toLowerCase();
                message = String.format("Player2 wins!: with a higher pair of %ss.", card);
            } else {
                if (p1Pair1 > p2Pair1) {
                    String card = player1.cards.get(p1Pos1).getRank().toString().toLowerCase();
                    message = String.format("Player1 wins!: with a higher lower pair of %ss.", card);
                } else if (p1Pair1 < p2Pair1) {
                    String card = player2.cards.get(p2Pos1).getRank().toString().toLowerCase();
                    message = String.format("Player2 wins!: with a higher lower pair of %ss.", card);
                }

                else {
                    for (int i = 4; i >= 0; i--) {
                        int highCard = p1Remaining[i];
                        int highCard2 = p2Remaining[i];
                        String card = player1.cards.get(i).getRank().toString().toLowerCase() +
                                " of " + player1.cards.get(i).getSuit().toString().toLowerCase();
                        String card2 = player2.cards.get(i).getRank().toString().toLowerCase() +
                                " of " + player2.cards.get(i).getSuit().toString().toLowerCase();
                        if (highCard == highCard2) {
                            continue;
                        } else if (highCard > highCard2) {
                            message = String.format("Player1 wins!: with a higher kicker of %s.", card);
                            break;
                        } else if (highCard < highCard2) {
                            message = String.format("Player2 wins!: with a higher kicker of %s.", card2);
                            break;
                        }
                    }
                }
            }
            break;
        case 3:
            message = getBestTrips(player1, player2);
            break;
        case 4:
            int hCard = player1.cards.get(4).getRank().ordinal();
            int hCard2 = player2.cards.get(4).getRank().ordinal();
            String p1card = player1.cards.get(4).getRank().toString().toLowerCase();
            String p2card = player2.cards.get(4).getRank().toString().toLowerCase();

            if (hCard == 12 && hCard2 == 12) {
                int nextCard = player1.cards.get(3).getRank().ordinal();
                int nextCard2 = player2.cards.get(3).getRank().ordinal();
                if (nextCard > nextCard2) {
                    message = String.format("Player1 wins!: with a royal straight.");
                }
                else {
                    message = String.format("Player2 wins!: with a royal straight.");
                }
            }
            else if (hCard > hCard2) {
                message = String.format("Player1 wins!: with higher straight up to %s.", p1card);
            }
            else {
                message = String.format("Player2 wins!: with higher straight up to %s.", p2card);
            }
            break;
        case 5:
            message = getNextBestCard(player1, player2);
            if (!message.equals("Tie.")) {
                message += " with flush.";
            }
            break;
        case 6:
            message = getBestTrips(player1, player2);
            break;
        case 7:
            int fourCard = player1.cards.get(2).getRank().ordinal();
            int fourCard2 = player2.cards.get(2).getRank().ordinal();
            String card = player1.cards.get(2).getRank().toString().toLowerCase();
            String card2 = player2.cards.get(2).getRank().toString().toLowerCase();

            if (fourCard > fourCard2) {
                message = String.format("Player1 wins!: with higher four of a kind of %ss.", card);
            }
            else {
                message = String.format("Player2 wins!: with higher four of a kind of %ss.", card2);
            }
            break;
        case 8:
            message = getNextBestCard(player1, player2);
            if (!message.equals("Tie.")) {
                message += " with straight flush.";
            }
            break;
        }

        return message;
    }

    /**
     * Displays the winner of the best hand
     * @param player1 hand of player one
     * @param player2 hand of player two
     * @return String message of the winner
     */
    public static String displayWinner(Hand player1, Hand player2) {
        String message;
        int score1 = player1.calcHandScore();
        int score2 = player2.calcHandScore();

        if (score1 > score2) {
            message = winnerMessage(player1, 1);
        }

        else if (score1 < score2) {
            message = winnerMessage(player2, 2);
        }

        else {
            message = tieBreaker(player1, player2);
        }

        return message;
    }
}
