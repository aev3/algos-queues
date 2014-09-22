/**
 * Created 2014-09-03.
 */

import java.util.Iterator;

/**
 * Interface for Deque implementations.
 *
 * @param <T>  - Generic item.
 */
public interface DequeIF<T>
{

    /**
     * Is the deque empty?
     *
     * @return
     */
    boolean isEmpty();

    /**
     * return the number of items on the deque
     *
     * @return
     */
    int size();

    /**
     * insert the item at the front
     *
     * @param item - generic item to be defined in callers code.
     */
    void addFirst(T item);

    /**
     * insert the item at the end
     *
     * @param item - generic item to be defined in callers code.
     */
    void addLast(T item);

    /**
     * delete and return the item at the front
     *
     * @return
     */
    T removeFirst();

    /**
     * delete and return the item at the end
     *
     * @return
     */
    T removeLast();

    /**
     * return an iterator over items in order from front to end
     *
     * @return
     */
    Iterator<T> iterator();
}
