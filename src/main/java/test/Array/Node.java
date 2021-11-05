package test.Array;

import lombok.Data;

@Data
public class Node {
    private Node previous;
    private Node next;
    private Object element;

    public Node(Node previous, Node next, Object element) {
        super();
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node(Object element) {
        this.element = element;
    }
}
