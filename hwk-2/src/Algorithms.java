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
        //Base case if the queue only has 1 element
        if (queue.size() <= 1) return;
        //Initialise the iterator to track the number of loops
        int iterator = 1;
        //Initialise elements outside of loop to stop them from being initialised each time the function loops
        T first;
        T next;
        do {
            first = queue.remove();
            for (int i = 0; i < queue.size(); i++) { //O(n)
                next = queue.remove();
                //Compare the first element to the next
                if (first.compareTo(next) > 0) {
                    queue.add(next);
                } else {
                    queue.add(first);
                    first = next;
                }
            }
            queue.add(first);
            iterator++;
            //Keep running this loop to account for worst case where the smallest element is at the end of the queue
        } while (iterator < queue.size()); //O(n-1)
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        //Calculate the numeric difference between the first 2 elements
        int difference = numbers[1] - numbers[0];
        //Return the difference if the Array is only 2 numbers
        if (numbers.length == 2) return difference;
        //run the recursive call to find the missing element
        return recursive(numbers, 1, difference);
    }

    /**
     * Recursively find the missing number in a sequence based on the predefined numeric difference
     *
     * @param numbers    int[], the array of numbers
     * @param index      int, the index to start from
     * @param difference int, the predefined difference between the first 2 elements of the array
     * @return int, the missing element
     */
    public static int recursive(int[] numbers, int index, int difference) {
        //If the next element does not equal the next expected value then return the expected value
        if (numbers[index] + difference != numbers[index + 1]) return numbers[index] + difference;
        //Else, keep running the recursion
        return recursive(numbers, index + 1, difference);
    }
}