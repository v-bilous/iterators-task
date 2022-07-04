import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class AggregateIteratorTest {

    private final Iterator<Integer>[] testInputIterators = new Iterator[]{
            Arrays.stream(new Integer[]{6, 8, 19, 21, 32, 66, 67, 77, 89}).iterator(),
            Arrays.stream(new Integer[]{1, 3, 5,  24, 33, 45, 57, 59, 89}).iterator(),
            Arrays.stream(new Integer[]{2, 4, 9,  18, 22, 44, 46, 89, 89}).iterator()
    };

    /**
     * Run to check the result of task only.
     * Following method is accepting input from the given task
     * Prints expected in task result.
     */
    @Test
    void runToCheckTheTask() {

        AggregateIterator testIterator = new AggregateIterator(testInputIterators);

        while (testIterator.hasNext()) {
            System.out.println(testIterator.nextLabeled());
        }
    }

    /**
     * Test to check if implementation is valid using given in the task iterators
     */
    @Test
    void shouldEqualToExpectedResult() {
        AggregateIterator testIterator = new AggregateIterator(testInputIterators);

        Integer[] expectedArray = new Integer[] {
                1, 2, 3, 4, 5, 6, 8, 9, 18, 19, 21, 22, 24, 32, 33, 44, 45, 46, 57, 59, 66, 67, 77, 89, 89, 89, 89
        };

        List<Integer> result = new ArrayList<>();
        while (testIterator.hasNext()) {
            result.add(testIterator.next());
        }

        Assertions.assertArrayEquals(expectedArray, result.toArray());
    }

    /**
     * Test to check if implementation is valid using custom iterators
     */
    @Test
    void shouldEqualToExpectedResultWithCustom() {

        final Iterator<Integer>[] testCustomInputIterators = new Iterator[]{
                Arrays.stream(new Integer[]{6, 8, 19, 21, 32, 66, 67, 77, 89}).iterator(),
                Arrays.stream(new Integer[]{1, 3, 5,  24, 33, 45, 57, 59, 89}).iterator(),
                Arrays.stream(new Integer[]{2, 4, 9,  18, 22, 44, 46, 89, 89}).iterator(),
                Arrays.stream(new Integer[]{10, 20, 30, 40, 50, 60, 65, 75, 85}).iterator()

        };
        AggregateIterator testIterator = new AggregateIterator(testCustomInputIterators);


        Integer[] expectedArray = new Integer[] {
                1, 2, 3, 4, 5, 6, 8, 9, 10, 18, 19, 20, 21, 22, 24, 30, 32, 33, 40, 44, 45, 46, 50, 57, 59, 60, 65, 66, 67, 75, 77, 85, 89, 89, 89, 89
        };

        List<Integer> result = new ArrayList<>();
        while (testIterator.hasNext()) {
            result.add(testIterator.next());
        }

        Assertions.assertArrayEquals(expectedArray, result.toArray());
    }

}