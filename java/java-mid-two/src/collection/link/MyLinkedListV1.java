package collection.link;

public class MyLinkedListV1 {
    private Node first;
    private int size = 0;

    public void add(Object e) {
        Node newNode = new Node(e);
        size++;
        if (first == null) {
            first = newNode;
            return;
        }

        Node lastNode = getLastNode();
        lastNode.next = newNode;
    }

    public Object set(int index, Object e) {
        Node x = getNode(index);
        Object old = x.item;
        x.item = e;
        return old;
    }

    public Object get(int index) {
        Node node = getNode(index);
        return node.item;
    }

    public int indexOf(Object e) {
        int index = 0;
        for (Node x = first; x != null; x = x.next) {
            if (e.equals(x.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyLinkedListV1{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }

    private Node getNode(int index) {
        Node x = first;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private Node getLastNode() {
        Node x = first;

        while (x.next != null) {
            x = x.next;
        }

        return x;
    }
}
