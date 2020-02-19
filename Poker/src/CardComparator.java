import java.util.Comparator;

/**
 *
 * @author jacob davis
 *
 * @version 1/9/20
 *
 * @param <E> card to compare
 */
public class CardComparator<E> implements Comparator<E>{

    /**
     * Compares the rank of two cards for ascending order
     *
     * @param arg0 first card compared to the second
     * @param arg1 second card compared to the first
     */
    @Override
    public int compare(E arg0, E arg1) {
        Card first = (Card) arg0;
        Card second = (Card) arg1;
        return first.getRank().compareTo(second.getRank());
    }

}
