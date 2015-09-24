/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class StackOfStrings {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public void push (String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop () {
        String result = first.item;
        first = first.next;
        return result;
    }

    public boolean isEmpty () {
        return (first == null);
    }

}