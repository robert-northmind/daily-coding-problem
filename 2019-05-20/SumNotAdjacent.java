import java.util.*;

class SumNotAdjacent {
    public static void main(String[] args) {
        int[] inputList1 = {5, 1, 1, 5};
        int sum1 = SumNotAdjacent.getSumNonAdjacent(inputList1);
        System.out.println("sum1: " + sum1);

        int[] inputList2 = {2, 4, 6, 2, 5};
        int sum2 = SumNotAdjacent.getSumNonAdjacent(inputList2);
        System.out.println("sum2: " + sum2);
    }

    public static int getSumNonAdjacent(int[] inputList) {
        if (inputList.length < 1) {
            return 0;
        }
        int inclusive = inputList[0];
        int exclusive = 0;

        for (int i = 1; i < inputList.length; i++) {
            int prev_exclusive = exclusive;
            exclusive = Math.max(exclusive, inclusive);
            inclusive = Math.max(prev_exclusive+inputList[i], inclusive);
        }

        return Math.max(exclusive, inclusive);
    }
}