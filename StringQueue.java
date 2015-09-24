/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class StringQueue {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public void enqueue (String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }



    }

    public String dequeue () {
        String result = first.item;
        first = first.next;
        if (isEmpty())  {
            last = null;
        }
        return result;


    }

    public boolean isEmpty () {
        return (first == null);

    }

}
