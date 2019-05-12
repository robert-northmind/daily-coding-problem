import java.util.*;

class SumInList {
    public static void main(String[] args) {
        int[] inputArray = {10, 15, 3, 7};
        int sumToMatch = 17;
        Boolean hasSum = SumInList.containsSum2(inputArray, sumToMatch);
        System.out.println("Has Sum: " + hasSum);
    }

    public static Boolean containsSum1(int[] inputList, int sumToMatch) {
        for (int i = 0; i < inputList.length-1; i++) {
            for (int j = i+1; j < inputList.length; j++) {
                if (inputList[i] + inputList[j] == sumToMatch) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean containsSum2(int[] inputList, int sumToMatch) {
        HashSet<Integer> counterPart = new HashSet<Integer>();
        for (int i = 0; i<inputList.length; i++) {
            int counterPartVal = sumToMatch - inputList[i];
            if (counterPart.contains(counterPartVal)) {
                return true;
            } else {
                counterPart.add(inputList[i]);
            }
        }
        return false;
    }
}