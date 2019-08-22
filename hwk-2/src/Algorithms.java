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
        int iterator = 1;
        T first;
        T next;
        do {
            first = queue.remove();
            for (int i = 0; i < queue.size(); i++) { //O(n)
                next = queue.remove();
                if (first.compareTo(next) > 0) {
                    queue.add(next);
                } else {
                    queue.add(first);
                    first = next;
                }
            }
            queue.add(first);
            iterator++;
        } while (iterator < queue.size()); //O(n-1)
//        System.out.println(queue.toString());
//        boolean change = true;
//        String firstString;
//        do {
//            T first = queue.remove();
//            firstString = first.toString();
//            for (int i = 0; i < queue.size(); i++) { //O(n)
//                T next = queue.remove();
//                if (first.compareTo(next) > 0) {
//                    queue.add(next);
//                } else {
//                    queue.add(first);
//                    first = next;
//                }
//                if (firstString.equals(first.toString())) change = false;
//            }
//            queue.add(first);
//        } while (change); //O(n-1)
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        int difference = numbers[1] - numbers[0];
        return recursive(numbers, 0, difference);
    }

    public static int recursive(int[] numbers, int index, int difference) {
        if (numbers[index] + difference != numbers[index + 1]) return numbers[index] + difference;
        return recursive(numbers, index + 1, difference);
    }
}