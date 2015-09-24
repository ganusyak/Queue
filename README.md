# Queue
This code implements #2 Assignment from course "Algorithms. pt1" from Coursera: https://class.coursera.org/algs4partI-009
Code passes all tests with score 100/100 (except bonus memory test)

Copyrighted by Yuriy Ganusyak
Feel free to contact me via:
mail: y.ganusayk@gmail.com
skype: y.ganusyak (voice call if you are not affraid my awfull accent and english :) )


Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports
adding and removing items from either the front or the back of the data structure. Create a generic data type Deque 
that implements the following API:

public class Deque<Item> implements Iterable<Item> {
   public Deque()                           // construct an empty deque
   public boolean isEmpty()                 // is the deque empty?
   public int size()                        // return the number of items on the deque
   public void addFirst(Item item)          // add the item to the front
   public void addLast(Item item)           // add the item to the end
   public Item removeFirst()                // remove and return the item from the front
   public Item removeLast()                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
}

Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure. Create a generic data type RandomizedQueue that implements the following API:

public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // remove and return a random item
   public Item sample()                     // return (but do not remove) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
}
