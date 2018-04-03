package Week6;

import java.util.*;
import java.io.*;

public class Partition3_Old {
	static boolean [][] Bm;
	static int numPart = 2;
    

	private static boolean partition3(int[]A, int numItem) {
		int sum = 0;
		for (int i=0; i<numItem; i++)
			sum+= A[i];
		if (sum % numPart != 0)
			return false;
		int groupSum = sum/numPart;
		Bm = new boolean[groupSum +1][numItem+1];
		// initialization
		for (int i=0; i<=groupSum; i++)
			Bm[i][0] = false;
		for (int i=0; i<=numItem; i++)
			Bm[0][i] = true;
		
		for (int i=1; i<=numItem; i++) {
			for (int j=1; j<=groupSum; j++) {
				Bm[j][i] = Bm[j][i-1];
				if (j >= A[i-1]) {
					Bm[j][i] = Bm[j][i] || Bm[j-A[i-1]][i-1]; 
				}
			}
		}
		
		return (Bm[sum/numPart][numItem]);
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        
        boolean ans = partition3(A, n);
        if (ans)
        	System.out.println("Can be divided equally into " + numPart + "groups.");
        else 
        	System.out.println("Cannot be divided equally into " + numPart + "groups.");
    }
}

