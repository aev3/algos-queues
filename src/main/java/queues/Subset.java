package edu.princeton.algos.queue;

import edu.princeton.util.StdIn;
import edu.princeton.util.StdOut;

/**
 *
 * <p>
 * This class implements client code for a subset program. It takes a command-line integer k, reads in a sequence of
 * N strings from standard input and prints out exactly k of them, uniformly at random. Each item from the sequence is
 * printed out at most once. It is assumed that k >= 0 and no greater than the number of strings on standard input.
 * </p>
 *
 *  <p>
 *  Subset client. Write a client program Subset.java that takes a command-line integer k; reads in a sequence of N
 *  strings from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random. Each
 *  item from the sequence can be printed out at most once. You may assume that k is greater than or equal to 0 and
 *  no greater than the number of string N on standard input.
 * <pre>
 {@code
 % echo A B C D E F G H I | java Subset 3       % echo AA BB BB BB BB BB CC CC | java Subset 8
 C                                              BB
 G                                              AA
 A                                              BB
 CC
 % echo A B C D E F G H I | java Subset 3       BB
 E                                              BB
 F                                              CC
 G                                              BB
 }
 * </pre>
 *
 *  </p>
 *
 *  <p>
 *  The running time of Subset must be linear in the size of the input. You may use only a constant amount of memory plus
 *  either one Deque or RandomizedQueue object of maximum size at most N, where N is the number of strings on standard
 *  input. (For an extra challenge, use only one Deque or RandomizedQueue object of maximum size at most k.) It should
 *  have the following API:
 *
 * <pre>
 {@code
 public class Subset {
 public static void main(String[] args)
 }
 }
 * </pre>
 * </p>
 */
public class Subset
{

    // main method, implements the subset client
    public static void main(String[ ] args)
    {
        RandomizedQueue<String> str = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            str.enqueue(StdIn.readString());

        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            StdOut.println(str.dequeue());
    }

}
