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

    @Override
    public boolean hasNext() {
        return set.isEmpty();
    }

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