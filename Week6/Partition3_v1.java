package Week6;

import java.util.*;
import java.io.*;

public class Partition3 {
	static int[][][] Bm;
    
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
	
	private static int partition3(int[]A, int numItem) {
		int sum = 0;
		for (int i=0; i<numItem; i++)
			sum+= A[i];
		if (sum % 3 != 0)
			return 0;
		// sort the A[] in non-increasing order
		ArrayList<Integer> aList = new ArrayList<Integer>();
		for (int k=0; k<A.length; k++) 
			aList.add(A[k]);
		Collections.sort(aList);
		Collections.reverse(aList);
		// call Knapsack without repetitions 3 times
		int ans = KnapsackWithoutRep(sum/3, A, A);
		return 0;
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        
        System.out.println(partition3(A, n));
    }
}

