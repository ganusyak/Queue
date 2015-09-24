
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yuriyganusyak on 9/23/15.
 * Implemented assignment #2 for Coursera course "Algorithms pt.1"
 * Randomized queue with such functionality:
 * public RandomizedQueue()                 // construct an empty randomized queue
 * public boolean isEmpty()                 // is the queue empty?
 * public int size()                        // return the number of items on the queue
 * public void enqueue(Item item)           // add the item
 * public Item dequeue()                    // remove and return a random item
 * public Item sample()                     // return (but do not remove) a random item
 * public Iterator<Item> iterator()         // return an independent iterator over items in random order
 * public static void main(String[] args)   // unit testing
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;                       // this array implements deque, resizable
    private int sizeOfStack;                    // Counter. Number of items in stack

    public RandomizedQueue() {
        // Initialize with array of one empty element ('null')
        array = (Item[]) new Object[1];
        sizeOfStack = 0;
    }

    public boolean isEmpty() {
        // true if stack is empty @ false otherwise
        return sizeOfStack == 0;
    }

    private int arraySize() {
        // helper method, returns actual size of array
        return array.length;
    }

    public int size() {
        // API methos, returns number of elements in stack
        return sizeOfStack;
    }


    private void resizeArrayTo(int size) {
        if (size < sizeOfStack) throw new IllegalArgumentException(); // incorrect argument protection

        //Copy contents of array to temporary newArray, and than assign array to its resized copy
        Item[] newArray = (Item[]) new Object[size];
        for (int i = 0; i < sizeOfStack; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        // Clear memory;
        newArray = null;
    }
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException(); // doesnt allow add null

        // Check if array needs to be resized to fit new data size, and resize if needed
        if (sizeOfStack == array.length) {
            resizeArrayTo(array.length * 2);
        }
        // insert new item and update counter
        array[sizeOfStack] = item;
        sizeOfStack++;
    }

    private int randomIndexInStack() {
        return StdRandom.uniform(sizeOfStack);
    }

    public Item dequeue() {
        if (sizeOfStack == 0) throw new java.util.NoSuchElementException(); // empty stack protection

        // Pick a random item from our stack and update counter
        int randomIndex = randomIndexInStack();
        Item result = array[randomIndex];
        sizeOfStack--;

        // move the last item to its place and nullify its old reference
        array[randomIndex] = array[sizeOfStack];
        array[sizeOfStack] = null;

        // resize array if there are too few elements in stack (less of equal to 1/4 of arraysize)
        if ((sizeOfStack <= (array.length / 4) && (array.length  > 1))) {
            resizeArrayTo(array.length / 2);
        }

        return result;

    }

    public Item sample() {
        if (sizeOfStack == 0) throw new java.util.NoSuchElementException(); // empty stack protection

        // Pick a random item from our stack
        return array[randomIndexInStack()];
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        // private array to store indexes of items of stack for iteration
        private int [] indexArray;
        private int counter = 0;

        public ListIterator() {
            createShuffledArray();
        }

        private void createShuffledArray() {
            // Creates array of indexes (of elements in stack) and randomizes it for random iteration
            indexArray = new int[sizeOfStack];
            for (int i = 0; i < sizeOfStack; i++) {
                indexArray[i] = i;
            }
            StdRandom.shuffle(indexArray);
        }

        @Override
        public Item next() {
            if (isEmpty() || counter == sizeOfStack) throw new NoSuchElementException(); // Stack is empty or reached
                                                                                         // the end during iteration

            // Item is array with "random" number from index array
            Item result = array[indexArray[counter]];
            counter++;
            return result;

        }

        @Override
        public boolean hasNext() {
            // if didnt reach end of array -> hasNext() = true
            return counter < sizeOfStack;
        }

        @Override
        public void remove() {
            // Unsupported operation in this assignment
            throw new java.lang.UnsupportedOperationException();
        }

    }

}