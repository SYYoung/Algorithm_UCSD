package Week4;

import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
        		b[0] = a[right];
            return numberOfInversions;
        }
        int ave = left + (right - left) / 2;
        int[] b1 = new int[ave-left+1];
        int[] b2 = new int[right-(ave+1)+1];
        numberOfInversions += getNumberOfInversions(a, b1, left, ave);
        numberOfInversions += getNumberOfInversions(a, b2, ave+1, right);
        //write your code here
        numberOfInversions += getMerge(b1, b2, b, left, right);
        return numberOfInversions;
    }
    
	public static int getMerge(int[]B, int[]C, int[] APrime, int left, int right) {
		// D <- empty array of size p+q
		// while B and C are both non-empty:
		//		b <- the first element of B
		//		c <- the first element of C
		//		if b<=c:
		//			move b from B to the end of D
		//		else:
		//			move c from C to the end of D
		//	move the rest of B and C to the end of D
		//	return D		
		int bIndex = 0, cIndex = 0, curr = 0;
		int countInverse = 0;
		while ((bIndex < B.length) && (cIndex < C.length)) {
			if (B[bIndex] <= C[cIndex]) {
				APrime[curr] = B[bIndex];
				bIndex++;
				curr++;
			}
			else {
				APrime[curr] = C[cIndex];
				// for those after bIndex, they are all inversions
				countInverse = (B.length - 1 - bIndex) + 1;
				cIndex++;
				curr++;
			}
		}
		if (bIndex == B.length) {
			for (int k=cIndex; k < C.length; k++) {
				APrime[curr] = C[k];
				curr++;
			}	
		}
		else if (cIndex == C.length) {
			for (int k=bIndex; k<B.length; k++) {
				APrime[curr] = B[k];
				curr++;
			}
		}
		return countInverse;
	}
	

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        
        int[] b = new int[a.length];
        System.out.println(getNumberOfInversions(a, b, 0, a.length-1));
        for (int k=0; k<a.length; k++) {
        		System.out.print("\t " + a[k]);
        }
    }
}

