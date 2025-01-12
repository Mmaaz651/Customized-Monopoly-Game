package upei.project;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    // Test the constructor and getData() method
    @Test
    public void testNodeInitialization() {
        // Given a node with integer data
        Node<Integer> node = new Node<>(5);

        // When we call getData
        Integer result = node.getData();

        // Then it should return the correct data
        assertEquals(5, result);
    }

    // Test that the next node reference is null when initialized
    @Test
    public void testNextIsNullInitially() {
        // Given a node
        Node<String> node = new Node<>("Hello");

        // When we check the next node
        Node<String> nextNode = node.next;

        // Then it should be null by default
        assertNull(nextNode);
    }

    // Test creating a linked list of two nodes
    @Test
    public void testLinkedNodes() {
        // Given two nodes
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> secondNode = new Node<>(2);

        // Link the first node to the second
        firstNode.next = secondNode;

        // When we get the data of the first and second nodes
        Integer firstData = firstNode.getData();
        Integer secondData = firstNode.next.getData();

        // Then it should return the correct values
        assertEquals(1, firstData);
        assertEquals(2, secondData);
    }

}
