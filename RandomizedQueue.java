
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int sizeOfStack;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        sizeOfStack = 0;
    }

    public boolean isEmpty() {
        return sizeOfStack == 0;
    }

    private int arraySize() {
        return array.length;
    }

    public int size() {
        return sizeOfStack;
    }

    private Item itemNumber(int number) {
        return array[number];
    }

    private void resizeArrayTo(int size) {
        Item[] newArray = (Item[]) new Object[size];
        for (int i = 0; i < sizeOfStack; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        newArray = null;
    }
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (sizeOfStack == array.length) {
            resizeArrayTo(array.length * 2);
        }
        array[sizeOfStack] = item;
        sizeOfStack++;
    }

    public Item dequeue() {
        if (sizeOfStack == 0) throw new java.util.NoSuchElementException();
        if (sizeOfStack == 1) {
            Item result = array[0];
            array[0] = null;
            sizeOfStack = 0;
            return result;
        }
        int randomIndex = StdRandom.uniform(0, sizeOfStack);
        Item result = array[randomIndex];
        sizeOfStack--;

        array[randomIndex] = array[sizeOfStack];
        array[sizeOfStack] = null;
        if ((sizeOfStack == (array.length / 4) && (array.length  > 1))) {
            resizeArrayTo(array.length / 2);
        }


        return result;

    }

    public Item sample() {
        if (sizeOfStack == 0) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(0, sizeOfStack);
        return array[randomIndex];
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        //private Node currentItem = first;
        //private int[] iterationOrder;
        private Item [] itemArray;
        private int counter = 0;

        private void createShuffledArray() {
            itemArray = (Item[]) new Object[sizeOfStack];
            for (int i = 0; i < sizeOfStack; i++) {
                itemArray[i] = array[i];
            }
            StdRandom.shuffle(itemArray);
        }

        @Override
        public Item next() {
            if (isEmpty() || counter == sizeOfStack) {
                throw new NoSuchElementException();
            }
            if (counter == 0) { createShuffledArray(); }
            Item result = itemArray[counter];

            counter++;
            if (counter == sizeOfStack) {
                itemArray = null;
            }
            return result;

        }

        @Override
        public boolean hasNext() {
            return counter < sizeOfStack;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

}