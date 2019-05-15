import java.util.*;

class ProductAllNumberList {
    public static void main(String[] args) {
        ArrayList<Integer> inputList1 = new ArrayList<Integer>(
            Arrays.asList(1,2,3,4,5)
        );
        ArrayList<Integer> prodList1 = ProductAllNumberList.getProdAllNumbers(inputList1);
        System.out.println("prodList1: " + prodList1);

        ArrayList<Integer> inputList2 = new ArrayList<Integer>(
            Arrays.asList(3,2,1)
        );
        ArrayList<Integer> prodList2 = ProductAllNumberList.getProdAllNumbers(inputList2);
        System.out.println("prodList2: " + prodList2);
    }

    public static ArrayList<Integer> getProdAllNumbers(ArrayList<Integer> inputList) {
        ArrayList<Integer> prodList = new ArrayList<Integer>();
        ArrayList<Integer> lowerProdList = new ArrayList<Integer>();
        ArrayList<Integer> upperProdList = new ArrayList<Integer>();

        lowerProdList.add(1);
        upperProdList.add(1);

        for (int i = 0; i<inputList.size()-1; i++) {
            Integer newValLow = lowerProdList.get(lowerProdList.size()-1)*inputList.get(i);
            lowerProdList.add(newValLow);

            Integer newValUp = upperProdList.get(0)*inputList.get(inputList.size()-1-i);
            upperProdList.add(0, newValUp);
        }
        for (int i = 0; i<lowerProdList.size(); i++) {
            prodList.add(lowerProdList.get(i)*upperProdList.get(i));
        }

        return prodList;
    }
}