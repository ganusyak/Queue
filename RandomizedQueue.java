
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
        array = (Item[])new Object[1];
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
        for (int i = 0; i < sizeOfStack; i++){
            newArray[i] = array[i];
        }
        array = newArray;
        newArray = null;
    }
    public void enqueue(Item item) {
        if (sizeOfStack == array.length) {
            resizeArrayTo(array.length * 2);
        }
        array[sizeOfStack] = item;
        sizeOfStack++;
    }

    public Item dequeue() {
        if (sizeOfStack == 0) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(0, sizeOfStack);

        sizeOfStack--;
        Item result = array[randomIndex];
        array[randomIndex] = array[sizeOfStack];

        if (sizeOfStack <= (array.length / 4)) {
            resizeArrayTo(array.length / 2);
        }

        array[sizeOfStack] = null;
        return result;

    }

    public Item sample() {
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



        @Override
        public Item next() {
            if (isEmpty() || counter == sizeOfStack) {
                throw new NoSuchElementException();
            }
            if (counter == 0) { StdRandom.shuffle(array, 0, sizeOfStack - 1); };
            Item result = array[counter];

            counter++;
            return result;
            //return randomNode.item;
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