import java.util.Iterator;

public class AggregateIterator implements Iterator<Integer> {

    private Iterator<Integer>[] inputIterators;
    private Integer[] nextItems;
    private int lastIteratorIndex = 0;

    public AggregateIterator(Iterator<Integer>[] inputIterators) {
        this.inputIterators = inputIterators;
        this.nextItems = new Integer[inputIterators.length];
    }

    @Override
    public boolean hasNext() {
        for (Integer nextItem : nextItems) {
            if (nextItem != null) {
                return true;
            }
        }
        for (Iterator<Integer> inputIterator : inputIterators) {
            if (inputIterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        for (int i = 0; i < nextItems.length; i++) {
            if (nextItems[i] == null && inputIterators[i].hasNext()) {
                nextItems[i] = inputIterators[i].next();
            }
        }
        Integer lowestInt = null;
        for (int i = 0; i < nextItems.length; i++) {
            if (lowestInt == null && nextItems[i] != null) {
                lowestInt = nextItems[i];
                lastIteratorIndex = i;
                continue;
            }
            if (lowestInt != null && nextItems[i] != null && lowestInt > nextItems[i]) {
                lowestInt = nextItems[i];
                lastIteratorIndex = i;
            }
        }
        nextItems[lastIteratorIndex] = null;
        return lowestInt;
    }

    public String nextLabeled() {
        return String.format("%s (from A%s)", next(), lastIteratorIndex + 1);
    }
}
