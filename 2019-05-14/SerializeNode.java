import java.util.*;

class SerializeNodes {
    public static void main(String[] args) {
        Node testNode = new Node("root", new Node("left", new Node("left.left"), null), new Node("right"));
        System.out.println("testNode: " + Node.serialize(testNode));
    }
}

class Node {
    private String value;
    private Node left;
    private Node right;

    Node(String value) {
        this.value = value;
    }

    Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static String serialize(Node node) {
        String serializedStr = "(";
        if (node.value != null) {
            serializedStr += node.value;
        }
        serializedStr += ",";
        if (node.left != null) {
            serializedStr += Node.serialize(node.left);
        }
        serializedStr += ",";
        if (node.right != null) {
            serializedStr += Node.serialize(node.right);
        }
        serializedStr += ")";
        return serializedStr;
    }

    public static Node deserialize(String nodeStr) {
        for (int i = 0; i < nodeStr.length(); i++) {
            char ch = nodeStr.charAt(i);
            if (ch == '(') {
                String subString = nodeStr.substring(i);
                return Node.deserialize(subString);
            } else {

            }
        }
        return new Node("");
    }
}
