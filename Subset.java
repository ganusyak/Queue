import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by yuriyganusyak on 9/24/15.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue queue = new RandomizedQueue();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            queue.enqueue(string);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}