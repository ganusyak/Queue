

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int counter;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public Deque() {
        first = null;
        last = null;
        counter = 0;
    }                         // construct an empty deque

    public int size() {
        if (isEmpty()) return 0;
        int sizeCounter = 1;
        Node currentNode = first;
        while (!(currentNode.next == null)) {
            sizeCounter++;
            currentNode = currentNode.next;
        }
        return sizeCounter;
    }                        // return the number of items on the deque

    public boolean isEmpty() {
        return counter == 0;
    }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        Node newFirst = new Node();
        newFirst.item = item;
        if (isEmpty()) {
            last = newFirst;
        } else {
            newFirst.next = first;
            first.previous = newFirst;
        }
        first = newFirst;
        counter++;
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        Node newLast = new Node();
        newLast.item = item;
        if (isEmpty()) {
            first = newLast;
        } else {
            newLast.previous = last;
            last.next = newLast;
        }
        last = newLast;
        counter++;

    }         // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item result = first.item;
        if (first.next == null) {
            // remove the last item in queue -> empty stack
            first = null;
            last = null;
        } else {
            // more than 1 element in stack
            Node newFirst = first.next;
            newFirst.previous = null;
            first = newFirst;
        }

        counter--;
        return result;

    }               // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item result = last.item;
        if (!(last.previous == null)) {
            Node newLast = last.previous;
            newLast.next = null;
            last = newLast;
        } else {
            first = null;
            last = null;
        }
        counter--;
        return result;
    }                // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node currentItem = first;

        @Override
        public Item next() {
            if (currentItem == null) throw new NoSuchElementException();
            Item result = currentItem.item;
            currentItem = currentItem.next;
            return result;
        }

        @Override
        public boolean hasNext() {
            return (currentItem != null);
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    // return an iterator over items in order from front to end
    public static void main(String[] args) {
    }    // unit testing
}
