
import java.util.Iterator;

/**
 * <p>
 * This class implements a randomized queue using a automatic resizing array.
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 * This implementation supports each randomized queue operation (besides
 * creating an iterator) in constant amortized time and uses space
 * proportional to the number of items currently in the queue.
 * </p>
 * <p/>
 * <p>
 * <b>Randomized queue.</b> A randomized queue is similar to a stack or
 * queue, except that the item removed is chosen uniformly at random from
 * items in the data structure. Create a generic data type RandomizedQueue
 * that implements the following API:
 * <p/>
 * <pre>
 * {@code
 * public class RandomizedQueue<T> implements Iterable<T> {
 * public RandomizedQueue()         // construct an empty randomized queue
 * public boolean isEmpty()         // is the queue empty?
 * public int size()                // return the number of items on the queue
 * public void enqueue(T t)         // add the item
 * public T dequeue()               // delete and return a random item
 * public T sample()                // return (but do not delete) a random item
 * public Iterator<T> iterator()    // return an independent iterator over items
 * in random order
 * public static void main(String[] args)   // unit testing
 * }
 * }
 * </pre>
 * <p/>
 * <p>
 * Throw a NullPointerException if the client attempts to add a null item;
 * throw a java.util.NoSuchElementException if the client attempts to sample
 * or dequeue an item from an empty randomized queue; throw an
 * UnsupportedOperationException, if the client calls the remove() method in
 * the iterator; throw a java.util.NoSuchElementException if the client calls
 * the next() method in the iterator and there are no more items to return.
 * </p>
 * <p/>
 * <p>
 * Your randomized queue implementation must support each randomized queue
 * operation (besides creating an iterator) in constant amortized time and
 * use space proportional to the number of items currently in the queue. That
 * is, any sequence of M randomized queue operations (starting from an empty
 * queue) should take at most cM steps in the worst case,
 * for some constant c. Additionally, your iterator implementation must
 * support construction in time linear in the number of items and it must
 * support the operations next() and hasNext() in constant worst-case time;
 * you may use a linear amount of extra memory per iterator. The order of
 * two or more iterators to the same randomized queue should be
 * mutually independent; each iterator must maintain its own random order.
 * </p>
 *
 * @param <T> - generic item that will be  referenced in caller/client code.
 */
public class RandomizedQueue<T> implements Iterable<T> {

    /**
     * Threshold for call to reduction strategy.
     */
    private static final int REDUCTION_THRESHOLD = 4;

    /**
     * <p>
     * Private inner class that manages the RandomizedQueueIterator.
     * </p>
     */
    private class RandomizedQueueIterator implements Iterator<T> {

        /**
         * Current node in queue.
         */
        private int current = 0;

        /**
         * Randomly shuffled array.
         */
        private T[] shuffled;

        /**
         * Constructor for inner class that implements the shuffle.
         */
        public RandomizedQueueIterator() {
            shuffled = (T[]) new Object[size];
            for (int i = head % queue.length; i < size; i++) {
                shuffled[i - (head % queue.length)] = queue[i];
            }
            StdRandom.shuffle(shuffled);
        }

        /**
         * Inner class method that determines whether there is a next item.
         *
         * @return boolean - True if queue has a next item, otherwise false.
         */
        public boolean hasNext() {
            return current < size;
        }

        /**
         * Method that acts as getter for next random item in queue.
         *
         * @return generic item - randomly shuffled next item
         */
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return shuffled[current++];
        }

        /**
         * DO Not Use. Will throw new UnsupportedOperationException.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * The actual queue array.
     */
    private T[] queue;

    /**
     * Position of first element in array.
     */
    private int head = 0;

    /**
     * Size of the queue array.
     */
    private int size = 0;

    /**
     * Position of last element in array.
     */
    private int tail = 0;

    /**
     * Construct an empty randomized queue.
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        queue = (T[]) new Object[2];
    }

    /**
     * Delete and return a random item.
     *
     * @return - generic item object
     */
    public final T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        exchange(head, (StdRandom.uniform(size) + head) % queue.length);
        final T item = queue[head];
        queue[head] = null;
        size--;
        head++;
        if (head == queue.length) {
            head = 0;
        }
        if (size > 0 && size == queue.length / REDUCTION_THRESHOLD) {
            resize(queue.length / 2);
        }

        return item;
    }

    /**
     * Add the item.
     *
     * @param item - generic object
     */
    public final void enqueue(final T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[tail++] = item;
        if (tail == queue.length) {
            tail = 0;
        }
        size++;
    }

    /**
     * Exchange two queue array positions.
     *
     * @param i - int containing value for ith element
     * @param j - int containing value for jth element
     */
    private void exchange(final int i, final int j) {
        final T swap = queue[i];
        queue[i] = queue[j];
        queue[j] = swap;
    }

    /**
     * Is the queue empty?
     *
     * @return boolean that reflects whether the queue is empty or not.
     */
    public final boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method that returns an independent iterator over items in random order.
     *
     * @return Iterator - an independent iterator over items in random order
     */
    public final Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * Method that resizes the current queue capacity to a new given one.
     *
     * @param capacity - int that reflect size/usage
     */
    private void resize(final int capacity) {
        assert capacity >= size;

        @SuppressWarnings("unchecked")
        final T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[(head + i) % queue.length];
        }
        queue = copy;
        head = 0;
        tail = size;
    }

    /**
     * Method that acts as getter for a random element.
     *
     * @return generic - return (but do not delete) a random item
     */
    public final T sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return queue[(StdRandom.uniform(size) + head) % queue.length];
    }

    /**
     * Method that acts as getter for size of queue.
     *
     * @return int - the number of items on the queue
     */
    public final int size() {
        return size;
    }

}
