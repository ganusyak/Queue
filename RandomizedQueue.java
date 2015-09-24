
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
    private int anotherInt;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public RandomizedQueue() {
        // empty queue
        first = null;
        last = null;
        sizeOfQueue = 0;
    }                         // construct an empty deque

    public int size() {
        return sizeOfQueue;
    }                        // return the number of items on the deque

    public boolean isEmpty() {
        return sizeOfQueue == 0;
    }

    private Node nodeWithNumber(int number) {
        Node currentNode = first;
        int counter = 0;
        while (counter < number) {
            currentNode = currentNode.next;
            counter++;
        }
        return currentNode;
    }

    public void enqueue(Item item) {      // add new node to the end of queue
        if (item == null) throw new java.lang.NullPointerException(); // if trying to add null Item

        Node newLast = new Node();
        newLast.item = item;
        if (isEmpty()) {
            first = newLast;
        } else {
            newLast.previous = last;
            last.next = newLast;
        }
        last = newLast;

        sizeOfQueue++;                     // increment size of queue counter

    }



    private Node randomNode() {
        int numberOfNodeToRemove = StdRandom.uniform(0, sizeOfQueue);
        return nodeWithNumber(numberOfNodeToRemove);
    }

    public Item dequeue() {              // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Node nodeToRemove = randomNode();

        Item result = nodeToRemove.item;
        if ((nodeToRemove.next == null) && (nodeToRemove.previous == null)) {
            // only one element exists
            first = null;
            last = null;
        } else if (nodeToRemove.next == null) {
            // last element in queue
            Node newLast = last.previous;
            newLast.next = null;
            last = newLast;
        } else if (nodeToRemove.previous == null) {
            // first element in queue
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

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Node randomNode = randomNode();
        return randomNode.item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        //private Node currentItem = first;
        //private int[] iterationOrder;
        private Item [] itemArray;
        private int counter = 0;

        private void createOrderArray() {
            //iterationOrder = new int[sizeOfQueue];
            itemArray = (Item[]) new Object[sizeOfQueue];
            Node currentNode = first;
            for (int i = 0; i < sizeOfQueue; i++) {
                //iterationOrder[i] = i;

                itemArray[i] = currentNode.item;
                currentNode = currentNode.next;
            }
            StdRandom.shuffle(itemArray);
            //return iterationOrder;
        }

        @Override
        public Item next() {
            if ((counter == sizeOfQueue) || (isEmpty())) {
                throw new NoSuchElementException();
            }
            if (counter == 0) {
                createOrderArray();
            }

            if (isEmpty()) throw new NoSuchElementException();
            //Node randomNode = nodeNumber(iterationOrder[counter]);
            Item result = itemArray[counter];
            counter++;
            if (counter == sizeOfQueue) itemArray = null;
            return result;
            //return randomNode.item;
        }

        @Override
        public boolean hasNext() {
            return ((counter < sizeOfQueue) && (!isEmpty()));
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }
    // return an iterator over items in order from front to end
    public static void main(String[] args) {
        // here will be unit testing
    }    // unit testing
}