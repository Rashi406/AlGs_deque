import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on stack

    // private int front;

    /**
     * private class ResizingArrayStack<Item>{ private Item[] a; // array of
     * items private int n; // number of elements on queue
     * 
     * 
     * private ResizingArrayStack() { a = (Item[]) new Object[2]; n = 0; }
     */

    public RandomizedQueue() // construct an empty randomized queue
    {
        a = (Item[]) new Object[2];
        n = 0;

    }

    public boolean isEmpty() // is the queue empty?
    {
        return n == 0;
    }

    public int size() // return the number of items on the queue
    {
        return n;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        else {
            if (n == a.length)
                resize(2 * (a.length));
            a[n++] = item;

        }

    }

    public Item dequeue() // remove and return a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(n);
        Item item = a[index];
        a[index] = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;

    }

    public Item sample() // return (but do not remove) a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(n);
        Item item = a[index];
        return item;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() // return an independent iterator over
                                     // items in random order
    {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private int[] s;

        private ArrayIterator() {
            i = n - 1;
            s = new int[n];
            for (int j = 0; j < n; j++)
                s[j] = j;
            StdRandom.shuffle(s);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return a[s[i--]];
        }
    }

    public static void main(String[] args) // unit testing
    {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        int i = 0;
        for (i = 1; i <= 12; i++)
            q.enqueue(i);
        System.out.println(q.size());
        Iterator<Integer> l = q.iterator();

        while (l.hasNext()) {
            int j = l.next();
            System.out.print(j + "->");

        }
        System.out.println("");
        System.out.println(q.sample());
        for (i = 1; i <= 6; i++) {
            int k = q.dequeue();
            System.out.println(k);
        }

        Iterator<Integer> h = q.iterator();
        while (h.hasNext()) {
            int j = h.next();
            System.out.print(j + "->");

        }
        System.out.println("");
        System.out.println(q.sample());

    }
}
