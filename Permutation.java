/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strs = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            strs.enqueue(line);
        }
        Iterator<String> s = strs.iterator();
        int i = 0;
        while (s.hasNext() && i < k) {
            StdOut.println(s.next());
            i++;
        }
    }
}
