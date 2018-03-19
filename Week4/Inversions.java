package Week4;

import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int ave = left + (right - left) / 2;
        int[] b1 = new int[ave-left+1];
        int[] b2 = new int[right-(ave+1)+1];
        numberOfInversions += getNumberOfInversions(a, left, ave);
        numberOfInversions += getNumberOfInversions(a, ave+1, right);
        //write your code here
        b1 = Arrays.copyOfRange(a, left, ave+1);
        b2 = Arrays.copyOfRange(a, ave+1, right+1);
        numberOfInversions += getMerge(a, b1, b2, left, right);
        return numberOfInversions;
    }
    
	public static int getMerge(int[] a, int[] b1, int[] b2, int left, int right) {
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
		int bIndex = 0, cIndex = 0, curr = left;
		int countInverse = 0;
		while ((bIndex < b1.length) && (cIndex < b2.length)) {
			if (b1[bIndex] <= b2[cIndex]) {
				a[curr] = b1[bIndex];
				bIndex++;
				curr++;
			}
			else {
				a[curr] = b2[cIndex];
				// for those after bIndex, they are all inversions
				countInverse = (b1.length - 1 - bIndex) + 1;
				cIndex++;
				curr++;
			}
		}
		if (bIndex == b1.length) {
			for (int k=cIndex; k < b2.length; k++) {
				a[curr] = b2[k];
				curr++;
			}	
		}
		else if (cIndex == b2.length) {
			for (int k=bIndex; k<b1.length; k++) {
				a[curr] = b1[k];
				curr++;
			}
		}
		
		System.out.println("inside mergeSort: left = " + left + ", right = " +right);
		for (int k=0; k<b1.length; k++)
			System.out.print("\t " + b1[k]);
		System.out.println();
		for (int k=0; k<b2.length; k++) 
			System.out.print("\t " + b2[k]);
		for (int k=left; k<= right; k++)
			System.out.print("\t " + a[k]);
		System.out.println();
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
        System.out.println(getNumberOfInversions(a, 0, a.length-1));
        for (int k=0; k<a.length; k++) {
        		System.out.print("\t " + a[k]);
        }
    }
}

