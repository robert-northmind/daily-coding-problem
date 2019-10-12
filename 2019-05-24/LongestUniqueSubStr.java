import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

class LongestUniqueSubStr {
    public static void main(String[] args) {
        String input = "abcba";
        LongestUniqueSubStr subStr = new LongestUniqueSubStr(input, 2);
        int longestSubStr = subStr.getLongestUniqueSubStr();
        System.out.println("longestSubStr: " + longestSubStr);
    }

    private String inputStr;
    private int maxNbrDistinct;

    LongestUniqueSubStr(String inputStr, int maxNbrDistinct) {
        this.inputStr = inputStr;
        this.maxNbrDistinct = maxNbrDistinct;
    }

    public int getLongestUniqueSubStr() {
        LinkedList linkedList = new LinkedList();
        HashMap<String, Integer> charIndexMap = new HashMap<String, Integer>();

        int minIndex = 0;
        int currIndex = 0;

        for (int i = 0; i<inputStr.length(); i++) {
            String aStr = String.valueOf(inputStr.charAt(i));
            
            Boolean newChar = !charIndexMap.containsKey(aStr);
            Boolean distinctCharsReached = charIndexMap.size() + (newChar?1:0) >= this.maxNbrDistinct;
            String firstVal = linkedList.firstVal();

            if (distinctCharsReached) {
                linkedList.popFirst();
            }
            if (!linkedList.lastVal().equals(aStr)) {
                linkedList.appendLast(aStr);
                charIndexMap.put(aStr, i);
            }

            charIndexMap.remove(firstVal);
        }

        return 1;
    }
}

public class LinkedList {
    private Node firstNode;
    private Node lastNode;

    LinkedList() {
    }

    public String firstVal() {
        if (this.firstNode != null) {
            return this.firstNode.value;
        } else {
            return null;
        }
    }

    public String lastVal() {
        if (this.lastNode != null) {
            return this.lastNode.value;
        } else {
            return null;
        }
    }

    public popFirst() {
        Node secondNode = this.firstNode.nextNode;
        this.firstNode = secondNode;
    }

    public appendLast(String val) {
        if (this.lastNode != null) {
            this.firstNode = new Node(val);
            this.lastNode = this.firstNode;
        } else {
            Node nextLastNode = new Node(val);
            this.lastNode.nextNode = nextLastNode;
            this.lastNode = nextLastNode;
        }
    }
}

class Node {
    public String value;
    public Node nextNode;
    public Node(String value) {
        this.value = value;
    }
}