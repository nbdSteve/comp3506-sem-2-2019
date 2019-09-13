import java.util.Iterator;
import java.util.Stack;

/**
 * An iterator for the tree ADT that performs a preorder traversal
 */
public class TreeIterator<E> implements Iterator<E> {
    private Stack<Tree<E>> set;
    private Tree<E> root;

    /**
     * Constructs a new tree iterator from the root of the tree to iterate over
     * <p>
     * You are welcome to modify this constructor but cannot change its signature
     * This method should have O(1) time complexity
     */
    public TreeIterator(Tree<E> root) {
        this.set = new Stack<>();
        this.root = root;
    }

    /**
     * Big O notation: O(1)
     * <p>
     * Worst case: Since this method runs in constant time, the worst case is
     * also the average case.
     * <p>
     * Amortised complexity: Methods do not differ
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return set.isEmpty();
    }

    /**
     * Big O notation: O(n)
     * <p>
     * Worst case: The worst case time complexity of this method would be the case
     * where the next tree node is a StandardTree that has many children. Each root
     * of the each children tree would be pushed to the stack, this has a time complexity
     * of O(n).
     * <p>
     * Amortised complexity: O(1), since majority of the calls are just going to be getting
     * the top of the stack they will run in constant time. However, if the push operation runs
     * it will result is a time complexity of O(n). Overall the amortised complexity would be O(1).
     *
     * @return E
     */
    @Override
    public E next() {
        if (hasNext()) {
            set.push(root);
            return root.getRoot();
        }
        Tree<E> current = set.pop();
        for (int i = current.getChildren().size() - 1; i >= 0; i--) {
            set.push(current.getChildren().get(i));
        }
        return set.peek().getRoot();
    }
}