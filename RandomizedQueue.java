import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int sizeOfQueue;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public RandomizedQueue() {
        first = null;
        last = null;
        int sizeOfQueue = 0;
    }                         // construct an empty deque

    public int size() {
        return sizeOfQueue;
    }                        // return the number of items on the deque

    public boolean isEmpty() {
        return sizeOfQueue == 0;
    }

    private Node nodeNumber(int number) {
        Node currentNode = first;
        int counter = 0;
        while (counter < number) {
            currentNode = currentNode.next;
            counter++;
        }
        return currentNode;
    }

    public void enqueue(Item item) {
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
        sizeOfQueue++;

    }         // add the item to the end

        // remove and return the item from the front

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int numberOfNodeToRemove = StdRandom.uniform(0, sizeOfQueue);
        Node nodeToRemove = nodeNumber(numberOfNodeToRemove);
        Item result = nodeToRemove.item;
        if ((nodeToRemove.next == null) && (nodeToRemove.previous == null)) {
            first = null;
            last = null;
        } else if (nodeToRemove.next == null) {
            Node newLast = last.previous;
            newLast.next = null;
            last = newLast;
        } else if (nodeToRemove.previous == null) {
            Node newFirst = first.next;
            newFirst.previous = null;
            first = newFirst;
        } else {
            Node prevNode = nodeToRemove.previous;
            Node nextNode = nodeToRemove.next;
            prevNode.next = nextNode;
            nextNode.previous = prevNode;
        }
        nodeToRemove = null;
        sizeOfQueue--;
        return result;
    }                // remove and return the item from the end

    public Item sample () {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int numberOfNodeToRemove = StdRandom.uniform(0, sizeOfQueue);
        Node nodeToRemove = nodeNumber(numberOfNodeToRemove);
        Item result = nodeToRemove.item;
        return result;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node currentItem = first;
        private int[] iterationOrder;
        private int counter = 0;

        private int[] createOrderArray() {
            iterationOrder = new int[sizeOfQueue];
            for (int i = 0; i < sizeOfQueue; i ++) {
                iterationOrder[i] = i;
            }
            StdRandom.shuffle(iterationOrder);
            return iterationOrder;
        }

        @Override
        public Item next() {
            if (counter == 0) {
                createOrderArray();
            }

            if (isEmpty()) throw new NoSuchElementException();
            Node randomNode = nodeNumber(iterationOrder[counter]);
            counter++;
            return randomNode.item;
        }

        @Override
        public boolean hasNext() {
            return (counter < sizeOfQueue);
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