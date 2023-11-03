public class BinTree<T extends Comparable<T>> {
    // The root node of the binary tree
    private Node<T> root;

    // Constructor initializes the binary tree with no root node (empty tree)
    BinTree() {
        root = null;
    }

    // Public method to insert a new value into the binary tree
    public void insert(T payload) {
        Node<T> node = new Node<>(null, null, payload); // Create a new node with the given payload
        root = insertRec(root, node); // Insert the node starting from the root
    }

    // Private recursive helper method to insert a new node into the tree
    private Node<T> insertRec(Node<T> currentNode, Node<T> newNode) {
        // If current node is null, this is the place to insert the new node
        if (currentNode == null) {
            return newNode;
        }

        // Decide to go left or right by comparing payload values
        if (newNode.getP().compareTo(currentNode.getP()) < 0) {
            // Insert in the left subtree
            currentNode.setL(insertRec(currentNode.getL(), newNode));
        } else {
            // Insert in the right subtree
            currentNode.setR(insertRec(currentNode.getR(), newNode));
        }

        // Return the (potentially modified) current node back up the call stack
        return currentNode;
    }

    // Public method to remove a node with a specific key
    public void remove(T key) {
        root = removeRec(root, null, key); // Start the removal process from the root
    }

    // Private recursive helper method to remove a node from the tree
    private Node<T> removeRec(Node<T> cur, Node<T> par, T key) {
        if (cur == null) return null; // If current node is null, nothing to do

        if (key.compareTo(cur.getP()) == 0) { // Node to be removed found
            if (cur.getL() == null) return cur.getR(); // Node has only right child or no child
            else if (cur.getR() == null) return cur.getL(); // Node has only left child

            // Node has two children: Find the smallest node in the right subtree
            cur.setP(findMin(cur.getR()).getP());

            // Delete the smallest node in the right subtree
            cur.setR(removeRec(cur.getR(), cur, cur.getP()));
        } else if (key.compareTo(cur.getP()) < 0) { // Key is in left subtree
            cur.setL(removeRec(cur.getL(), cur, key));
        } else { // Key is in right subtree
            cur.setR(removeRec(cur.getR(), cur, key));
        }

        return cur; // Return the (potentially modified) current node
    }

    // Utility method to find the minimum value node in a subtree rooted at given node
    private Node<T> findMin(Node<T> root) {
        while (root.getL() != null) {
            root = root.getL(); // Keep going left to find the minimum value
        }
        return root; // Found the minimum value node
    }

    // Public search method to find a node with the given key
    public T search(T key) {
        Node<T> check = searchRec(root, key); // Start the search from the root
        return (check != null) ? check.getP() : null; // Return the payload or null if not found
    }

    // Private recursive helper method to search for a node
    private Node<T> searchRec(Node<T> cur, T key) {
        if (cur == null || key.compareTo(cur.getP()) == 0) {
            return cur; // Base case: found the key or reached the end of a branch
        }

        if (key.compareTo(cur.getP()) < 0) {
            // Key is smaller than current node's payload; search left subtree
            return searchRec(cur.getL(), key);
        } else {
            // Key is larger than current node's payload; search right subtree
            return searchRec(cur.getR(), key);
        }
    }

    // Public method to display the tree in an in-order traversal
    public void displayReport() {
        displayReportRec(root); // Start the in-order traversal from the root
    }

    // Private recursive helper method to perform an in-order traversal of the tree
    private void displayReportRec(Node<T> node) {
        if (node == null) {
            return; // Base case: end of branch
        }

        // First, visit left child
        displayReportRec(node.getL());

        // Visit current node
        System.out.println(node.toString());

        // Finally, visit right child
        displayReportRec(node.getR());
    }
}
