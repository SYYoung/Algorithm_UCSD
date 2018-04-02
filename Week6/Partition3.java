package Week6;

import java.util.*;
import java.io.*;

public class Partition3 {
	static int[][][] Bm;
    

	private static int partition3(int[]A, int numItem) {
		int sum = 0;
		for (int i=0; i<numItem; i++)
			sum+= A[i];
		if (sum % 3 != 0)
			return 0;
		Bm = new int[sum/3 +1][sum/3 +1][numItem+1];
		// initialization
		Bm[0][0][0] = 1;
		for (int i=0; i<=sum/3; i++)
			for (int j=0; j<=sum/3; j++)
				Bm[i][j][0] = 0;
		for (int i=1; i<=numItem; i++) {
			for (int x=1; x<=sum/3; x++) {
				for (int y=1; y<= sum/3; y++) {
					Bm[x][y][i] = Bm[x][y][i-1] |
									Bm[x-A[i-1]][y][i-1] |
									Bm[x][y-A[i-1]][i];
				}
			}
		}
		if (Bm[sum/3][sum/3][numItem] == 1)
			return 1;
		else 
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

