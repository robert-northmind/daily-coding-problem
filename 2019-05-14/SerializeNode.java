import java.util.*;

class SerializeNodes {
    public static void main(String[] args) {
        Node testNode = new Node("root", new Node("left", new Node("left.left"), null), new Node("right"));
        
        String serialized = Node.serialize(testNode);
        Node deserialized = Node.deserialize(serialized);

        System.out.println("Deserialized: " + deserialized.left.left.value);
    }
}

class Node {
    public String value;
    public Node left;
    public Node right;

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
        return Node.internalSerialize(nodeStr, 0).node;
    }

    private static NodeSerial internalSerialize(String nodeStr, int index) {
        char ch = nodeStr.charAt(index);
        if (ch != '(') {
            return new NodeSerial(index+1, null);
        }

        String value = "";
        Node left = null;
        Node right = null;

        index++;
        ch = nodeStr.charAt(index);
        while (ch != ',') {
            value += ch;
            index++;
            ch = nodeStr.charAt(index);
        }

        index++;
        ch = nodeStr.charAt(index);

        if (ch == '(') {
            NodeSerial nodeSerial = Node.internalSerialize(nodeStr, index);
            left = nodeSerial.node;
            index = nodeSerial.index;
        }

        index++;
        ch = nodeStr.charAt(index);

        if (ch == '(') {
            NodeSerial nodeSerial = Node.internalSerialize(nodeStr, index);
            right = nodeSerial.node;
            index = nodeSerial.index;
        }

        index++;

        return new NodeSerial(index,new Node(value, left, right));
    }
}

class NodeSerial {
    public int index;
    public Node node;
    NodeSerial(int index, Node node) {
        this.index = index;
        this.node = node;
    }
}
