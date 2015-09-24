

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by yuriyganusyak on 9/23/15.
 * Implemented assignment #2 for Coursera course "Algorithms pt.1"
 * Doubleended queue (deque), which can add and remove elements from both sides
 * API:
 * public Deque()                           // construct an empty deque
 * public boolean isEmpty()                 // is the deque empty?
 * public int size()                        // return the number of items on the deque
 * public void addFirst(Item item)          // add the item to the front
 * public void addLast(Item item)           // add the item to the end
 * public Item removeFirst()                // remove and return the item from the front
 * public Item removeLast()                 // remove and return the item from the end
 * public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 * public static void main(String[] args)   // unit testing
 *
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;            // first element of deque
    private Node last;             // last elements of deque
    private int counter;           // number of elements in deque

    private class Node {
        // Private class node, stores:
        private Item item;         // Contents of the element
        private Node next;         // link to the next element of deque
        private Node previous;     // link to the next element of deque
    }

    public Deque() {
        // Inintialize empty deque
        first = null;              // no first or last element, links to both are null
        last = null;
        counter = 0;               // zero elements in deque
    }

    public int size() {
        // return the number of items on the deque
        return counter;
    }                        // return the number of items on the deque

    public boolean isEmpty() {
        // is the deque empty?
        return counter == 0;
    }

    public void addFirst(Item item) {
        // Adds an element to the start of deque
        if (item == null) throw new java.lang.NullPointerException(); // Can't add "null"

        // Create new node, set its "Item" value and link it with existing elements if possible
        Node newFirst = new Node();
        newFirst.item = item;
        if (isEmpty()) {
            last = newFirst;
        } else {
            newFirst.next = first;
            first.previous = newFirst;
        }
        // Set new node as the first element of deque and update counter
        first = newFirst;
        counter++;
    }

    public void addLast(Item item) {
        // Adds an element to the end of deque
        if (item == null) throw new java.lang.NullPointerException(); // Can't add "null"

        // Create new node, set its "Item" value and link it with existing elements if possible
        Node newLast = new Node();
        newLast.item = item;
        if (isEmpty()) {
            first = newLast;
        } else {
            newLast.previous = last;
            last.next = newLast;
        }
        // Set new node as the last element of deque and update counter
        last = newLast;
        counter++;

    }

    public Item removeFirst() {
        // Removes the first element from deque and returns it
        if (isEmpty()) throw new java.util.NoSuchElementException(); // Can't remove an element from an empty deque

        // Get a value of te first element and disconnect it from other elements if needed
        Item result = first.item;
        if (first.next == null) {
            // remove the only item in queue -> empty stack
            first = null;
            last = null;
        } else {
            // more than 1 element in stack
            Node newFirst = first.next;
            newFirst.previous = null;
            first = newFirst;
        }

        // Update counter and return the "Item" value of first element
        counter--;
        return result;

    }

    public Item removeLast() {
        // Removes the last element from deque and returns it
        if (isEmpty()) throw new java.util.NoSuchElementException(); // Can't remove an element from an empty deque

        // Get a value of the last element and disconnect it from other elements if needed
        Item result = last.item;
        if ((last.previous == null)) {     // empty stack
            // remove the only item in queue -> empty stack
            first = null;
            last = null;
        } else {
            // more than 1 element in stack
            Node newLast = last.previous;
            newLast.next = null;
            last = newLast;
        }

        // Update counter and return the "Item" value of first element
        counter--;
        return result;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node currentItem = first;

        @Override
        public Item next() {
            // Public iteration method
            // returns next item in iteration
            if (currentItem == null) throw new NoSuchElementException();
            Item result = currentItem.item;
            currentItem = currentItem.next;
            return result;
        }

        @Override
        public boolean hasNext() {
            // public iteration method, returns whether iteration has next element
            // if didnt reach end of array -> hasNext() = true
            return (currentItem != null);
        }

        @Override
        public void remove() {
            // Unsupported operation in this assignment
            throw new java.lang.UnsupportedOperationException();
        }

    }

    // return an iterator over items in order from front to end
    public static void main(String[] args) {
    }    // unit testing
}
