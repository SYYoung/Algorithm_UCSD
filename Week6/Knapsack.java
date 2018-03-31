package Week6;

import java.util.*;

public class Knapsack {
    static int greedyAlg(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
          if (result + w[i] <= W) {
            result += w[i];
          }
        }
        return result;
    }

	public static int KnapsackWithoutRep(int W, int[] wList, int[] vList) {
		// recurrence formula:
		//	max{value(w-wi, i-1)+vi, value(w,i-1)}
		// 1st one: including wi, 2nd: wi is not used
		// init all value(0,j) <- 0
		// init all value(w,0) <- 0
		int numItem = wList.length;
		int[][] value = new int[numItem+1][W+1];
		for (int k=0; k<=numItem; k++) 
			value[k][0] = 0;
		for (int k=0; k<=W; k++)
			value[0][k] = 0;
		//	for i from 1 to n:
		//		for w from 1 to W:
		//			value(w,i) <- value(w,i-1)
		//			if wi <= w:
		//				val <- value(w-wi, i-1) + vi
		//				if value(w,i)<val
		//					value(w,) <- val
		for (int i=1; i<=numItem; i++) {
			for (int w=1; w<=W; w++) {
				value[i][w] = value[i-1][w];
				if (wList[i-1] <= w) {
					int val = value[i-1][w-wList[i-1]] + vList[i-1];
					if (val > value[i][w]) {
						value[i][w] = val;
					}
				}
			}
		}
		return value[numItem][W];
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        int[] vList = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        // in this problem, all bars have same unit of value. therefore, we make up vList
        for (int k=0; k<n; k++)
        	vList[k] = w[k];
        //System.out.println(greedyAlg(W, w));
        int ans = KnapsackWithoutRep(W, w, vList);
        System.out.println("The value without repetition = " + ans);
    }
}

