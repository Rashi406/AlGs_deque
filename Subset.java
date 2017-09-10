import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;

public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (StdIn.hasNextLine() && !StdIn.isEmpty()) {

            q.enqueue(StdIn.readString());
        }
        Iterator<String> l = q.iterator();
        for (int i = 0; i < k; i++)
            System.out.println(l.next());

    }

}
