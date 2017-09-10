import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n; // size of the queue
    private Node first, last;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public Deque() {

        n = 0;
    }

    public boolean isEmpty() // is the deque empty?
    {
        return n == 0;
    }

    public int size() // return the number of items on the deque
    {
        return n;
    }

    public void addFirst(Item item) // add the item to the front
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        else {
            Node nw = new Node(item, first, null);
            if (first != null)
                first.prev = nw;
            first = nw;
            if (last == null)
                last = nw;

            n++;
        }

    }

    public void addLast(Item item) // add the item to the end
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        else {
            Node nw = new Node(item, null, last);
            if (last != null)
                last.next = nw;
            last = nw;
            if (first == null)
                first = nw;

            n++;
        }
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item; // save item to return
        first = first.next;
        first.prev = null; // delete first node
        n--;
        return item;
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item; // save item to return
        last = last.prev;
        last.next = null; // delete last node
        n--;
        return item;
    }

    public Iterator<Item> iterator() // return an iterator over items in order
                                     // from front to end
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) // unit testing
    {
        Deque<Integer> q = new Deque<Integer>();
        int i = 0;
        for (i = 1; i <= 12; i++) {
            if (i <= 6)
                q.addFirst(i);
            else
                q.addLast(i);

        }
        Iterator<Integer> l = q.iterator();
        while (l.hasNext()) {
            int j = l.next();
            System.out.print(j + "->");

        }
        System.out.println("");
        System.out.println(q.size());
        for (i = 1; i <= 6; i++) {
            if (i <= 3)
                q.removeFirst();
            else
                q.removeLast();
        }
        Iterator<Integer> h = q.iterator();
        while (h.hasNext()) {
            int j = h.next();
            System.out.print(j + "->");

        }
        System.out.println("");
        System.out.println(q.size());

    }

}
