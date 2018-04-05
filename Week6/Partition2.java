package Week6;

import java.util.*;
import java.io.*;

public class Partition2 {
	boolean [][] Bm;
	static int numPart = 2;
    

	private boolean partition2(int[]A, int numItem) {
		int sum = 0;
		for (int i=0; i<numItem; i++)
			sum+= A[i];
		if (sum % numPart != 0)
			return false;
		int groupSum = sum/numPart;
		Bm = new boolean[numItem +1][groupSum+1];
		
		// initialization
		for (int i=0; i<=numItem; i++)
			Bm[i][0] = true;
		for (int i=1; i<=groupSum; i++)
			Bm[0][i] = false;
		
		for (int i=1; i<=numItem; i++) {
			for (int j=1; j<=groupSum; j++) {
				Bm[i][j] = Bm[i-1][j];
				if (j >= A[i-1]) {
					Bm[i][j] = Bm[i][j] || Bm[i-1][j-A[i-1]]; 
				}
				System.out.print("\t" + Bm[i][j]);
			}
			System.out.println();;
		}
		if (Bm[numItem][groupSum])
			reconstruct(A, Bm, groupSum);
		return (Bm[numItem][groupSum]);
	}
	
	private void reconstruct(int[] A, boolean[][] Bm, int groupSum) {
		int row = A.length;
		int col = groupSum;
		ArrayList<Integer> aList = new ArrayList<Integer>();
		
		while (row >0 ) {
			if (Bm[row-1][col]) {
				// item A[col-1] is not included.
				row = row-1;
			}
			else if (Bm[row-1][col-A[row-1]]) {
				aList.add(A[row-1]);
				col = col - A[row-1];
				row = row-1;
			}
		}
		for (int i : aList)
			System.out.println("the list includes: " + i);
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        
        Partition2 part = new Partition2();
        boolean ans = part.partition2(A, n);
        if (ans)
        	System.out.println("Can be divided equally into " + numPart + "groups.");
        else 
        	System.out.println("Cannot be divided equally into " + numPart + "groups.");
    }
}

