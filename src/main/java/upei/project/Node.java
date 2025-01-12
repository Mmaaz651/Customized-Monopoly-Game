package upei.project;

/**
 * Represents a node in a linked list.
 * The Node class holds data of type T and a reference to the next node in the list.
 */
public class Node<T> {
    private T data;
    public Node<T> next;

    /**
     * Constructor to initialize the node with data.
     * @param data the data to store in the node.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData(){
        return this.data;
    }
}
