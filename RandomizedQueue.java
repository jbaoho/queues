/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.s = (Item[]) new Object[1];
        this.n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // resize if full
        if (this.n == this.s.length) {
            Item[] temp = (Item[]) new Object[this.s.length * 2];
            for (int i = 0; i < this.s.length; i++) {
                temp[i] = this.s[i];
            }
            this.s = temp;
        }
        // add item to end of array
        this.s[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // resize if 1/4
        if (this.n <= this.s.length / 4) {
            Item[] temp = (Item[]) new Object[this.s.length / 2];
            for (int i = 0; i < this.n; i++) {
                temp[i] = this.s[i];
            }
            this.s = temp;
        }
        int r = StdRandom.uniformInt(n);
        if (r == n - 1) {
            Item temp = s[--n];
            s[n] = null;
            return temp;
        }
        Item temp = s[r];
        s[r] = s[--n];
        s[n] = null;
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return s[StdRandom.uniformInt(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = n;


        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int r = StdRandom.uniformInt(i);
            if (r == i - 1) {
                return s[--i];
            }
            Item temp = s[r];
            s[r] = s[--i];
            s[i] = temp;
            return temp;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String[] strs = new String[] { "to", "be", "or", "not", "to", "be" };
        for (String str : strs) {
            rq.enqueue(str);
        }
        StdOut.println("Iterator: ");
        for (String value : rq) {
            StdOut.println(value);
        }
        StdOut.println("Sample: " + rq.sample());
        StdOut.println("Dequeue " + rq.dequeue());
    }
}
