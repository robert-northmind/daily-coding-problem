import java.util.*;

class FirstMissingPositive {
    public static void main(String[] args) {
        int[] inputList1 = {3,4,-1,1};
        int minPositive1 = FirstMissingPositive.findMinPositive(inputList1);

        int[] inputList2 = {2, 3, 7, 6, 8, -1, -10, 15};
        int minPositive2 = FirstMissingPositive.findMinPositive(inputList2);

        int[] inputList3 = { 2, 3, -7, 6, 8, 1, -10, 15 };
        int minPositive3 = FirstMissingPositive.findMinPositive(inputList3);

        int[] inputList4 = { 1,2,0 };
        int minPositive4 = FirstMissingPositive.findMinPositive(inputList4);


        System.out.println("minPositive: " + minPositive1);
        System.out.println("minPositive: " + minPositive2);
        System.out.println("minPositive: " + minPositive3);
        System.out.println("minPositive: " + minPositive4);
    }

    public static int findMinPositive(int[] inputList) {
        for (int i = 0; i<inputList.length; i++) {
            if (inputList[i] <= 0) {
                inputList[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i<inputList.length; i++) {
            int absValue = Math.abs(inputList[i]);
            if (absValue < inputList.length && inputList[absValue-1] >= 0) {
                inputList[absValue-1] = inputList[absValue-1] * -1;
            }
        }

        int firstPositive = 1;
        for (int i = 0; i<inputList.length; i++) {
            if (inputList[i] > 0) {
                firstPositive = i+1;
                break;
            }
        }
        return firstPositive;
    }
}