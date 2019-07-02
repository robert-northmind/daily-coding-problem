import java.util.*;

class EvalArithmeticTree {
    public static void main(String[] args) {

        ArithmeticNode thirdNode1 = new ArithmeticNode("3");
        ArithmeticNode thirdNode2 = new ArithmeticNode("2");
        ArithmeticNode secondNode1 = new ArithmeticNode("+", thirdNode1, thirdNode2);

        ArithmeticNode thirdNode3 = new ArithmeticNode("4");
        ArithmeticNode thirdNode4 = new ArithmeticNode("5");
        ArithmeticNode secondNode2 = new ArithmeticNode("+", thirdNode3, thirdNode4);

        ArithmeticNode topNode = new ArithmeticNode("*", secondNode1, secondNode2);

        System.out.println("Arithmetic value: " + topNode.evaluateNode());
    }
}

class ArithmeticNode {
    private String value;
    private ArithmeticNode left;
    private ArithmeticNode right;

    ArithmeticNode(String value) {
        this.value = value;
    }

    ArithmeticNode(String value, ArithmeticNode left, ArithmeticNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Double evaluateNode() {
        if (left == null && right == null) {
            return Double.parseDouble(this.value);
        } else if (left != null && right != null) {
            Double leftVal = left.evaluateNode();
            Double rightVal = right.evaluateNode();

            Double finalVal = 0.0;
            if (value.equals("+")) {
                finalVal = leftVal + rightVal;
            } else if (value.equals("-")) {
                finalVal = leftVal - rightVal;
            } else if (value.equals("*")) {
                finalVal = leftVal * rightVal;
            } else if (value.equals("/")) {
                finalVal = leftVal / rightVal;
            }
            return finalVal;
        } else {
            throw new AssertionError();
        }
    }
}