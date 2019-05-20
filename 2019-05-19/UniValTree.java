import java.util.*;

class UniValTree {
    public static void main(String[] args) {
        MyNode left = new MyNode(1);
        MyNode rightleft = new MyNode(new MyNode(1), new MyNode(1), 1);
        MyNode rightright = new MyNode(0);
        MyNode right = new MyNode(rightleft, rightright, 0);
        MyNode node = new MyNode(left, right, 0);

        int nbrUniVal = node.nbrUniValNodesFast();
        System.out.println("nbrUniVal: " + nbrUniVal);
    }
}

class MyNode {
    MyNode left;
    MyNode right;
    int value;

    public MyNode(MyNode left, MyNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public MyNode(int value) {
        this.value = value;
    }

    public Boolean isUniVal() {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.value == right.value && right.isUniVal() && left.isUniVal();
        }  else {
            return false;
        }
    }

    public int nbrUniValNodes() {
        int nbrUniVal = this.isUniVal() ? 1 : 0;
        if (this.left != null) {
            nbrUniVal += left.nbrUniValNodes();
        }
        if (this.right != null) {
            nbrUniVal += right.nbrUniValNodes();
        }
        return nbrUniVal;
    }

    public int nbrUniValNodesFast() {
        if (this.left == null && this.right == null) {
            return 1;
        }
        int leftNbrUniVal = this.left != null ? left.nbrUniValNodesFast() : 0;
        int rightNbrUniVal = this.right != null ? right.nbrUniValNodesFast() : 0;
        int nbrUniVal = leftNbrUniVal + rightNbrUniVal;
        if (leftNbrUniVal > 0 && rightNbrUniVal > 0 && this.left.value == this.value && this.right.value == this.value) {
            nbrUniVal += 1;
        }
        return nbrUniVal;
    }
}
