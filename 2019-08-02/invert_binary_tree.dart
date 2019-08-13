void main() {
  print("Hello");

  Node f_Node = Node(value: "f");

  Node e_Node = Node(value: "e");
  Node d_Node = Node(value: "d");

  Node c_Node = Node(value: "c", left: f_Node);
  Node b_Node = Node(value: "b", left: d_Node, right: e_Node);

  Node a_Node = Node(value: "a", left: b_Node, right: c_Node);
  Node.printTree(a_Node);
  a_Node._invertNode();
  Node.printTree(a_Node);
}

class Node {
  final String value;
  Node left;
  Node right;

  Node({this.value, this.left, this.right});

  static printTree(Node root) {
    String nodes = root._printNode("", "", "");
    print(nodes);
  }

  _invertNode() {
    if (left != null) {
      left._invertNode();
    }
    if (right != null) {
      right._invertNode();
    }
    Node tmpNode = right;
    right = left;
    left = tmpNode;
  }

  String _printNode(String mainString, String prefix, String childrenPrefix) {
    mainString += "${prefix}${value}\n";
    if (left != null && right != null) {
      mainString = left._printNode(
          mainString, childrenPrefix + "├── ", childrenPrefix + "│   ");
      mainString = right._printNode(
          mainString, childrenPrefix + "└── ", childrenPrefix + "    ");
    } else if (left != null) {
      mainString = left._printNode(
          mainString, childrenPrefix + "└── ", childrenPrefix + "    ");
    } else if (right != null) {
      mainString = right._printNode(
          mainString, childrenPrefix + "└── ", childrenPrefix + "    ");
    }
    return mainString;
  }
}
