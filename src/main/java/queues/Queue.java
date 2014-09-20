package edu.princeton.algos.queue;

import edu.princeton.util.StdIn;
import edu.princeton.util.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The <tt>edu.princeton.algos.queue.Queue</tt> class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p/>
 * This implementation uses a singly-linked list with a static nested class for
 * linked-list nodes. See {@link edu.princeton.util.LinkedQueue} for the version from the
 * textbook that uses a non-static nested class.
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 * <p/>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class Queue<T> implements Iterable<T>
{

    /**
     * number of elements on queue
     */
    private int N;

    /**
     * beginning of queue
     */
    private Node<T> first;

    /**
     * end of queue
     */
    private Node<T> last;

    /**
     * helper linked list class
     *
     * @param <T>
     */
    private static class Node<T>
    {
        /**
         * Nested class to define nodes
         */
        private T item;
        private Node<T> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue()
    {
        first = null;
        last = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size()
    {
        return N;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     *
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T peek()
    {
        if(isEmpty())
            throw new NoSuchElementException(
                    "edu.princeton.algos.queue.Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to the end of queue.
     *
     * @param item the item to add
     */
    public void enqueue(T item)
    {
        Node<T> oldlast = last;
        last = new Node<T>();
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     *
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T dequeue()
    {
        if(isEmpty())
            throw new NoSuchElementException(
                    "edu.princeton.algos.queue.Queue underflow");
        T t = first.item;
        first = first.next;
        N--;
        if(isEmpty())
            last = null;   // to avoid loitering
        return t;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for(T item : this)
            s.append(item + " ");
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()
    {
        return new ListIterator<T>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<T> implements Iterator<T>
    {

        private Node<T> current;

        public ListIterator(Node<T> first)
        {
            current = first;
        }

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public T next()
        {
            if(!hasNext())
                throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the <tt>edu.princeton.algos.queue.Queue</tt> data type.
     */
    public static void main(String[] args)
    {
        Queue<String> q = new Queue<String>();
        while(!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if(!item.equals("-"))
                q.enqueue(item);
            else if(!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
