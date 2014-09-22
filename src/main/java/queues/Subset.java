
/**
 * <p>
 * This class implements client code for a subset program. It takes a
 * command-line integer k, reads in a sequence of  * printed out at most
 * once. It is assumed that k >= 0 and no greater than the number of strings on
 * standard input.
 * </p>
 *
 * <p>
 * Subset client. Write a client program Subset.java that takes a command-line
 * integer k; reads in a sequence of N strings from standard input using
 * StdIn.readString(); and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once. You may
 * assume that k is greater than or equal to 0 and no greater than the number
 * of string N on standard input.
 *
 * <p>
 * The running time of Subset must be linear in the size of the input. You may
 * use only a constant amount of memory plus either one Deque or
 * RandomizedQueue object of maximum size at most N, where N is the number of
 * strings on standard input. (For an extra challenge, use only one Deque or
 * RandomizedQueue object of maximum size at most k.) It should have the
 * following API:
 * <p/>
 * <pre>
 * {@code
 * public class Subset {
 * public static void main(String[] args)
 * }
 * }
 * </pre>
 * </p>
 */
public final class Subset {
    /**
     * No argument constructor made private to avoid instantiation.
     */
    private Subset() {
    }

    /**
     * Convenience main method for CLI/IDE that implements the subset client.
     *
     * @param args - String array containing program arguments.
     *             [Not implemented]
     */
    public static void main(final String[] args) {
        RandomizedQueue<String> str = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            str.enqueue(StdIn.readString());
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(str.dequeue());
        }
    }

}
