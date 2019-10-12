class MinNbrCoins {
    public static void main(String[] args) {
        // int amount = 16;
        // int[] coinValues = { 25, 10, 5, 1 };
        // int amount = 5;
        // int[] coinValues = { 3, 2, 1 };
        int amount = 121;
        int[] coinValues = { 1000, 500, 100, 50, 20, 10, 5, 2, 1 };

        int minNbrCoins1 = getMinCoinsForCents(amount, coinValues);
        System.out.println("1: Min nbr coins for n: " + amount + " is: " + minNbrCoins1);

        int minNbrCoins2 = minCoinDynamic(amount, coinValues);
        System.out.println("2: Min nbr coins for n: " + amount + " is: " + minNbrCoins2);
    }

    public static int getMinCoinsForCents(int amount, int[] coins) {
        int nbrNeededCoins = 0;
        int centsLeftToProcess = amount;
        int coinValueIndex = 0;
        while (centsLeftToProcess > 0 && coinValueIndex < coins.length) {
            int coinValue = coins[coinValueIndex];
            int nbrCoinsUsedForValue = centsLeftToProcess / coinValue;
            centsLeftToProcess -= coinValue * nbrCoinsUsedForValue;
            coinValueIndex += 1;
            nbrNeededCoins += nbrCoinsUsedForValue;
        }
        return nbrNeededCoins;
    }

    public static int minCoinDynamic(int amount, int[] coins) {
        int[] nbrNeededCoins = new int[amount + 1];
        nbrNeededCoins[0] = 0;

        for (int currAmount = 1; currAmount <= amount; currAmount++) {
            nbrNeededCoins[currAmount] = Integer.MAX_VALUE;

            for (int coinsIndex = 0; coinsIndex < coins.length; coinsIndex++) {
                int coinValue = coins[coinsIndex];
                if (coinValue <= currAmount) {
                    int remainingAmount = currAmount - coinValue;
                    int currMinCoins = nbrNeededCoins[remainingAmount] + 1;
                    int prevMinCoins = nbrNeededCoins[currAmount];
                    nbrNeededCoins[currAmount] = Math.min(prevMinCoins, currMinCoins);
                }
            }
        }

        return nbrNeededCoins[amount];
    }
}