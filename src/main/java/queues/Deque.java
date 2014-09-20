
package edu.princeton.algos.queue;

import java.util.Iterator;


/**
 * <p>
 * This class implements a double-ended queue (deque) using a doubly linked list. A deque is a generalization of a stack
 * and a queue that supports inserting and removing items from either the front or the back of the data structure. This
 * implementation supports each deque operation in constant worst-case time and uses space proportional to the number
 * of items currently in the deque.
 * </p>
 *
 * <p>
 * Write a generic data type for a deque and a randomized queue. The goal of this assignment is t0 implement elementary
 * data structures using arrays and linked lists, and to introduce you to generics and iterators.
 * </p>
 *
 * <p>
 * <b>Dequeue:</b> A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that
 * supports inserting and removing items from either the front or the back of the data structure. Create a generic data
 * type Deque that implements the following API:
 *
 * <pre>
 {@code
 public class Deque<Item> implements Iterable<Item> {
 public Deque()                           // construct an empty deque
 public boolean isEmpty()                 // is the deque empty?
 public int size()                        // return the number of items on the deque
 public void addFirst(Item item)          // insert the item at the front
 public void addLast(Item item)           // insert the item at the end
 public Item removeFirst()                // delete and return the item at the front
 public Item removeLast()                 // delete and return the item at the end
 public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 public static void main(String[] args)   // unit testing
 }
 }
 * </pre>
 *
 * </p>
 *
 *  <p>
 *  Throw a NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException
 *  if the client attempts to remove an item from an empty deque; throw an UnsupportedOperationException if the client
 *  calls the remove() method in the iterator; throw a java.util.NoSuchElementException if the client calls the next()
 *  method in the iterator and there are no more items to return.
 *  </p>
 *
 *  <p>
 *  Your deque implementation must support each deque operation in constant worst-case time and use space proportional to
 *  the number of items currently in the deque. Additionally, your iterator implementation must support the operations
 *  next() and hasNext() (plus construction) in constant worst-case time and use a constant amount of extra space per
 *  iterator.
 *  </p>
 *
 */
public class Deque<T> implements Iterable<T>
{

    private int size;       // size of the deque
    private final DequeNode<T> head;       // first node (sentinel)
    private final DequeNode<T> tail;       // last node (sentinel)

    // construct an empty deque
    public Deque( )
    {
        size = 0;
        head = new DequeNode<T>();
        tail = new DequeNode<T>();
        head.next = tail;
        tail.prev = head;
    }

    // insert the item at the front
    public void addFirst(final T item)
    {
        if (item == null)
            throw new NullPointerException();

        final DequeNode<T> first = head.next;
        final DequeNode<T> node = new DequeNode<T>();
        node.item = item;
        node.next = first;
        node.prev = head;
        head.next = node;
        first.prev = node;
        size++;
    }

    // insert the item at the end
    public void addLast(final T item)
    {
        if (item == null)
            throw new NullPointerException();

        final DequeNode<T> last = tail.prev;
        final DequeNode<T> node = new DequeNode<T>();
        node.item = item;
        node.next = tail;
        node.prev = last;
        tail.prev = node;
        last.next = node;
        size++;
    }

    // is the deque empty?
    public boolean isEmpty( )
    {
        return size == 0;
    }

    // return an iterator over items in order from front to end
    public Iterator<T> iterator( )
    {
        return new DequeIterator();
    }

    // delete and return the item at the front
    public T removeFirst( )
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        final DequeNode<T> first = head.next;
        final DequeNode<T> next = first.next;
        next.prev = head;
        head.next = next;
        size--;

        return first.item;
    }

    // delete and return the item at the end
    public T removeLast( )
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        final DequeNode<T> last = tail.prev;
        final DequeNode<T> prev = last.prev;
        tail.prev = prev;
        prev.next = tail;
        size--;

        return last.item;
    }

    // class to implement the deque iterator
    private class DequeIterator implements Iterator<T>
    {
        private DequeNode<T> current = head.next;

        public boolean hasNext( )
        {
            return current != tail;
        }

        public T next( )
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            final T item = current.item;
            current = current.next;
            return item;
        }

        public void remove( )
        {
            throw new UnsupportedOperationException();
        }
    }

    // return the number of items on the deque
    public int size( )
    {
        return size;
    }

    // class to store each deque node
    private static class DequeNode<T>
    {

        private T item;    // the actual item
        private DequeNode<T> next;    // pointer to next node
        private DequeNode<T> prev;    // pointer to previous node
    }

    public static void main(String ... args)
    {
      
      
        Deque<String> dequeue = new Deque<String>();
        dequeue.addFirst("See how they run. ");
        dequeue.addFirst("See how they run. ");
        dequeue.addFirst("Three blind mice. ");
        dequeue.addFirst("Three blind mice. ");
        dequeue.addLast("They all ran after the farmer's wife. ");
        dequeue.addLast("Who cut off their tails with a carving knife. ");
        dequeue.addLast("Did you ever see such a sight in your life. ");
        dequeue.addLast("As three blind mice? ");
        int dequeSize = dequeue.size();
        System.out.println("dequeSize() = " + dequeSize);
        Iterator<String> iterator = dequeue.iterator();
        while(iterator.hasNext())
        {
            String next = iterator.next().toString();
            System.out.println(next);
        }
    }

}

/* See: http://www.cs.princeton.edu/courses/archive/fall12/cos226/assignments.php */
