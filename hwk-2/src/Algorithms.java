import java.util.Queue;

/**
 * Write your solution to this assignment in this class
 */
public class Algorithms {

    /**
     * Write your implementation of the sortQueue algorithm her
     *
     * @param queue the queue to sort
     */
    public static <T extends Comparable<T>> void sortQueue(Queue<T> queue) {
        // base case if the queue only has 1 or 0 elements
        if (queue.size() <= 1) return;
        // initialise the iterator to track the number of loops
        int iterator = 1;
        // initialise elements outside of loop to stop them from being
        // initialised each time the function loops
        T first;
        T next;
        do {
            first = queue.remove();
            for (int i = 0; i < queue.size(); i++) { //O(n)
                next = queue.remove();
                // compare the first element to the next
                if (first.compareTo(next) > 0) {
                    queue.add(next);
                } else {
                    queue.add(first);
                    first = next;
                }
            }
            queue.add(first);
            iterator++;
            // keep running this loop to account for worst case where the
            // smallest element is at the end of the queue
        } while (iterator < queue.size()); //O(n-1)
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        // store the arithmetic pattern as an int
        int difference;
        // switch the length of the Array to handle base cases
        switch (numbers.length) {
            case 2:
                // return the difference between the two elements
                return numbers[1] - numbers[0];
            case 1:
                return numbers[0];
            case 0:
                return 0;
            default:
                // calculate the numeric difference between the first 2 elements
                difference = numbers[1] - numbers[0];
                break;
        }
        // run the recursive call to find the missing element
        return findMissingNumberRecursively(numbers, 1, difference); // O(n)
    }

    /**
     * Recursively find the missing number in a sequence based
     * on the predefined numeric difference
     *
     * @param numbers    int[], the array of numbers
     * @param index      int, the index to start from
     * @param difference int, the predefined difference between
     *                   the first 2 elements of the array
     * @return int, the missing element
     */
    public static int findMissingNumberRecursively(int[] numbers, int index, int difference) {
        // base case if the sequence is not missing an element
        if (index == numbers.length - 1) return 0;
        // if the next element does not equal the next expected value
        // then return the expected value
        if (numbers[index] + difference != numbers[index + 1])
            return numbers[index] + difference;
        // else, keep running the recursion
        return findMissingNumberRecursively(numbers, index + 1, difference);
    }
}