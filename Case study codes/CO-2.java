
import java.util.*;

class BPlusTree {
    static class Node {
        boolean isLeaf;
        List<Long> keys = new ArrayList<>();
        List<Node> children = new ArrayList<>();
        List<String> values = new ArrayList<>(); // only for leaf
        Node next;

        Node(boolean leaf) { this.isLeaf = leaf; }
    }

    Node root;

    public BPlusTree() { root = new Node(true); }

    // Search operation
    public String search(long key) {
        Node cur = root;
        while (!cur.isLeaf) {
            int i = 0;
            while (i < cur.keys.size() && key >= cur.keys.get(i)) i++;
            cur = cur.children.get(i);
        }
        for (int i = 0; i < cur.keys.size(); i++)
            if (cur.keys.get(i) == key) return cur.values.get(i);
        return null;
    }

    // Insert (simplified placeholder)
    public void insert(long key, String value) {
        Node leaf = root; // assume single node for simplicity
        leaf.keys.add(key);
        leaf.values.add(value);
    }

    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree();
        tree.insert(101, "VideoA");
        System.out.println(tree.search(101));
    }
}