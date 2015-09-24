import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yuriyganusyak on 9/24/15.
 */

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] array;
    int sizeOfStack;

    public FixedCapacityStack() {
        array = (Item[])new Object[4];
        sizeOfStack = 0;
    }

    public boolean isEmpty() {
        return sizeOfStack == 0;
    }

    public int arraySize() {
        return array.length;
    }

    public int getSizeOfStack() {
        return sizeOfStack;
    }

    public Item itemNumber(int number) {
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
