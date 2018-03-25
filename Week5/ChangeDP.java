package Week5;

import java.util.Scanner;

public class ChangeDP {
	static int[] coins = {1, 3, 4};
    private static int getChange(int amt) {
		// MinNumCoins(0) <- 0
		//	for m from 1 to money:
		//		MinNumCoins(m) >- infinity
		//		for i from 1 to |coins|:
		//			if m >= coin[i]:
		//				NumCoins <- MinNumCoins(m-coin[i]) + 1
		//				if NumCoins < MinNumCoins(m):
		//					MinNumCoins(m) <- NumCoins
		//	return MinNumCoins(money)
        int[] minNumCoins = new int[amt+1];
        int[][] coinList = new int[amt+1][3];
        coinList[0][0] = 0; coinList[0][1] = 0; coinList[0][2] = 0;
        int minIndex = 0;
        minNumCoins[0] = 0;
        for (int m = 1; m<=amt; m++) {
        		minNumCoins[m] = Integer.MAX_VALUE;
        		coinList[m][0] = 0; coinList[m][1] = 0; coinList[m][2] = 0;
        		for (int k=0; k<coins.length; k++) {
        			if (m >= coins[k]) {
        				int numCoins = minNumCoins[m-coins[k]] + 1;
        				if (numCoins < minNumCoins[m]) {
        					minNumCoins[m] = numCoins;
        					minIndex = k;
        				}
        			}
        		}
        		coinList[m][0] = coinList[m-coins[minIndex]][0]; 
        		coinList[m][1] = coinList[m-coins[minIndex]][1];
        		coinList[m][2] = coinList[m-coins[minIndex]][2];
        		coinList[m][minIndex]++;
        		
        }
        // print out the result
        System.out.println("This is output");
        for (int k=0; k<coins.length; k++)
        		System.out.println("coin value : " + coins[k] + ", number = " + coinList[amt][k]);
        
        return minNumCoins[amt];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

