
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>edu.princeton.algos.queue.Queue</tt> class represents a
 * first-in-first-out (FIFO) queue of generic items. It supports the usual
 * <em>enqueue</em> and <em>dequeue</em> operations, along with methods for
 * peeking at the first item, testing if the queue is empty, and iterating
 * through the items in FIFO order.
 * <p/>
 * This implementation uses a singly-linked list with a static nested class
 * for linked-list nodes.
 * <p/>
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and
 * <em>is-empty</em> operations all take constant time in the worst case.
 * <p/>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 *
 * @param <T> - generic item that will be  referenced in caller/client code.
 *
 */
public class Queue<T> implements Iterable<T> {

    /**
     * number of elements on queue.
     */
    private int num;

    /**
     * beginning of queue.
     */
    private Node<T> first;

    /**
     * end of queue.
     */
    private Node<T> last;

    /**
     * Private static helper for linked list class.
     *
     * @param <T> - generic item that will be  referenced in caller/client code.
     *
     */
    private static class Node<T> {

        /**
         * Nested class to define linked list nodes.
         */
        private T item;

        /**
         * Nested class to define next node in linked list.
         */
        private Node<T> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        num = 0;
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    public final boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public final int size() {
        return num;
    }

    /**
     * Returns the item least recently added to this queue or throws
     * java.util.NoSuchElementException if this queue is empty.
     *
     * @return the item least recently added to this queue
     *
     */
    public final T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "edu.princeton.algos.queue.Queue underflow");
        }
        return first.item;
    }

    /**
     * Adds the item to the end of queue.
     *
     * @param item - the item to add to the end of the queue
     */
    public final void enqueue(final T item) {
        Node<T> swap = last;
        last = new Node<T>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            swap.next = last;
        }
        num++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added
     * or throws java.util.NoSuchElementException if this queue is empty.
     *
     * @return the item on this queue that was least recently added
     *
     */
    public final T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "edu.princeton.algos.queue.Queue underflow");
        }
        T t = first.item;
        first = first.next;
        num--;
        if (isEmpty()) {
            last = null;
        }
        return t;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public final String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in
     * FIFO order.
     *
     * @return an iterator that iterates over the items in this queue
     */
    public final Iterator<T> iterator() {
        return new ListIterator<T>(first);
    }

    /**
     * Private class that will hold state for the Iterator.
     *
     * @param <T> - generic item that will be  referenced in caller/client code.
     */
    private class ListIterator<T> implements Iterator<T> {

        /**
         * Current node in linked list.
         */
        private Node<T> current;

        /**
         * First node in linked list.
         *
         * @param f - the first node in the linked list
         */
        public ListIterator(final Node<T> f) {
            current = f;
        }

        /**
         * Method to determine  whether the linked list has a next node.
         *
         * @return - boolean that reflects status of current node; i.e., it is
         * or is not null.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * An iterator, doesn't need to implement remove() since it's optional.
         * If called this method will throw new UnsupportedOperationException.
         * (So don't call this method.)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Next node in linked list.
         *
         * @return - <T> generic item as determined by method
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Convenience main method for CLI/IDE testing.
     *
     * @param args  - String array containing program arguments.
     *              [Not implemented]
     */
    public static void main(final String[] args) {
        Queue<String> q = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                StdOut.print(q.dequeue() + " ");
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
