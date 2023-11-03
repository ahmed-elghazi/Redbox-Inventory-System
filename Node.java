public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    // Fields for the left and right children of the node and the payload data
    private Node<T> Left;
    private Node<T> Right;
    private T Payload;

    // Default constructor initializing the node with no children and no payload
    Node() {
        Left = null;
        Right = null;
        Payload = null;
    }

    // Constructor with parameters for left and right children and the payload
    Node(Node<T> L, Node<T> R, T P) {
        Left = L;
        Right = R;
        Payload = P;
    }

    // Constructor that only initializes the payload, with no children
    Node(T P) {
        Left = null;
        Right = null;
        Payload = P;
    }

    // Getter for the left child
    public Node<T> getL() {
        return Left;
    }

    // Setter for the left child
    public void setL(Node<T> L) {
        Left = L;
    }

    // Getter for the right child
    public Node<T> getR() {
        return Right;
    }

    // Setter for the right child
    public void setR(Node<T> R) {
        Right = R;
    }
    
    // Setter for the payload
    public void setP(T P) {
        Payload = P;
    }

    // Getter for the payload
    public T getP() {
        return Payload;
    }

    // Overridden toString method to return the string representation of the payload
    @Override
    public String toString(){
        return Payload != null ? Payload.toString() : "null";
    }

    // Overridden compareTo method to compare two nodes based on their payloads
    @Override
    public int compareTo(Node<T> A) {
        if (this.Payload == null && A.getP() == null) {
            return 0; // Both payloads are null, considered equal
        } else if (this.Payload == null) {
            return -1; // This payload is null, thus considered 'less' than A's payload
        } else if (A.getP() == null) {
            return 1; // A's payload is null, thus this payload is considered 'greater'
        }

        // Delegates the comparison to the compareTo method of the payload
        return this.Payload.compareTo(A.getP());
    }
}
