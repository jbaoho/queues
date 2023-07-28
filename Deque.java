/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int count;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.isEmpty()) {
            this.first = new Node();
            this.first.item = item;
            this.last = first;
        }
        else {
            Node n = new Node();
            n.item = item;
            n.next = first;
            this.first.prev = n;
            this.first = n;
        }
        this.count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.isEmpty()) {
            this.last = new Node();
            this.last.item = item;
            this.first = last;
        }
        else {
            Node n = new Node();
            n.item = item;
            this.last.next = n;
            n.prev = this.last;
            this.last = n;
        }
        this.count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (this.count == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.first = this.first.next;
            this.first.prev = null;
        }
        this.count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if (this.count == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.last = last.prev;
            this.last.next = null;
        }
        this.count--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Node n = current;
            current = current.next;
            return n.item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> deck = new Deque<>();
        String[] strs = new String[] { "to", "be", "or", "not", "to", "be" };
        deck.addFirst(strs[0]);
        deck.addFirst(strs[1]);
        deck.addLast(strs[2]);
        deck.addLast(strs[3]);
        deck.addFirst(strs[4]);
        deck.addLast(strs[5]);
        Iterator<String> s = deck.iterator();
        while (s.hasNext()) {
            StdOut.println(s.next());
        }
        StdOut.println(deck.removeFirst());
        StdOut.println(deck.removeLast());
    }
}
