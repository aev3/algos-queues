/**
 * Created 2014-09-03.
 */

package edu.princeton.algos.queue;

import java.util.Iterator;

public interface DequeIF<T>
{

    /**
     * Is the deque empty?
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * return the number of items on the deque
     *
     * @return
     */
    public int size();

    /**
     * insert the item at the front
     *
     * @param item
     */
    public void addFirst(T item);

    /**
     * insert the item at the end
     *
     * @param item
     */
    public void addLast(T item);

    /**
     * delete and return the item at the front
     *
     * @return
     */
    public T removeFirst();

    /**
     * delete and return the item at the end
     *
     * @return
     */
    public T removeLast();

    /**
     * return an iterator over items in order from front to end
     *
     * @return
     */
    public Iterator<T> iterator();
}
