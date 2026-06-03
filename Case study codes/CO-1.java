class Node {
    double key;
    int height;
    int size;
    Node left, right;

    Node(double key) {
        this.key = key;
        this.height = 1;
        this.size = 1;
    }
}

public class OrderStatisticTree {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int size(Node n) {
        return (n == null) ? 0 : n.size;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    void update(Node n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);

        return y;
    }

    Node insert(Node node, double key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else
            node.right = insert(node.right, key);

        update(node);

        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Rank of key
    int getRank(Node node, double key) {
        if (node == null)
            return 0;

        if (key < node.key)
            return getRank(node.left, key);

        else if (key > node.key)
            return size(node.left) + 1 + getRank(node.right, key);

        else
            return size(node.left) + 1;
    }

    public static void main(String[] args) {
        OrderStatisticTree tree = new OrderStatisticTree();

        double[] ratings = {3.2, 3.8, 4.1, 4.3, 4.5, 4.7, 4.8, 4.9};

        for (double r : ratings) {
            tree.root = tree.insert(tree.root, r);
        }

        double target = 4.3;

        int rank = tree.getRank(tree.root, target);

        System.out.println("Ratings inserted successfully.");
        System.out.println("Target Rating: " + target);
        System.out.println("Rank of " + target + " is: " + rank);
        System.out.println("Time Complexity: O(log n)");
    }
}