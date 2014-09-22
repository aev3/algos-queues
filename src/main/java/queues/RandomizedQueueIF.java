/**
 * Created 2014-09-15.
 */

import java.util.Iterator;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random from items in
 * the data structure. Create a generic data type RandomizedQueue that
 * implements the following default constructor:
 * <code>
 * class RandomizedQueue<Item> implements Iterable<Item> { ... }
 * </code>
 */

/**
 * <p>
 * Randomized queue. A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random from items in
 * the data structure. Create a generic data type RandomizedQueue that
 * implements the following API:
 * <pre>
 * {@code
 * class RandomizedQueue<Item> implements Iterable<Item> {
 * RandomizedQueue()          // construct an empty randomized queue
 * boolean isEmpty()          // is the queue empty?
 * int size()                 // return the number of items on the queue
 * void enqueue(Item item)    // add the item
 * Item dequeue()             // delete and return a random item
 * Item sample()              // return (but do not delete) a random item
 * Iterator<Item> iterator()  // return an independent iterator over items in
 * // random order
 * static void main(String[] args)   // unit testing
 * }
 * }
 * </pre>
 * </p>
 * <p/>
 * <p> Throw a NullPointerException if the client attempts to add a null
 * item; throw a java.util.NoSuchElementException, if the client attempts to
 * sample or dequeue an item from an empty randomized queue; throw an
 * UnsupportedOperationException if the client calls the remove() method in
 * the iterator; throw a java.util.NoSuchElementException if the client calls
 * the next() method in the iterator and there are no more items to return.
 * </p>
 * <p/>
 * <p>
 * The RandomizedQueue implementation must support each randomized queue
 * operation (besides creating an iterator) in constant amortized time and
 * use space proportional to the number of items currently in the queue. That
 * is, an sequence of M randomized queue operations (starting from an empty
 * queue) should take at most cM steps in the worst case,
 * for some constant c. Additionally, your iterator implementation must
 * support construction in time linear in the number of items and it must
 * support the operations next() and hasNext() in constant worst-case time;
 * you may use linear amount of extra memory per iterator. The order of two
 * or more iterators to the same randomized queue should be mutually
 * independent; each iterator must maintain its own random order.
 * </p>
 *
 * @param <T> - generic item to be defined by calling code.
 */
public interface RandomizedQueueIF<T> {
    /**
     * Is the queue empty?
     *
     * @return  - boolean that reflect the status of the Queue; i.e.,
     * is the queue empty.
     */
    boolean isEmpty();

    /**
     * Return the number of items on the queue.
     *
     * @return - int containing the number of items on the queue.
     */
    int size();

    /**
     * Add the item.
     *
     * @param t - Generic item to be added to queue.
     */
    void enqueue(T t);

    /**
     * Delete and return a random item.
     *
     * @return - Generic random item to be returned from the queue.
     */
    T dequeue();

    /**
     * Return (but do not delete) a random item.
     *
     * @return  - Generic item chosen randomly from queue
     */
    T sample();

    /**
     * Return an independent iterator over items in random order.
     *
     * @return  - an independent iterator over items in random order
     */
    Iterator<T> iterator();
}
