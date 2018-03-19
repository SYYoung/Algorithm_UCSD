package Week4;

import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {
	
   private  static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length-1;
        while (left <= right) {
        		int middle = (left + right)/2;
        		if (x == a[middle]) 
        			return middle;
        		else if (x < a[middle]) {
        			right = middle - 1;
        		}
        		else {
        			left = middle + 1;
        		}		
        }
        return right;
    }


    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here
        // 1. sort the starting points, then sort the end point. 
        // 3. then use the binary search to find # of starts <= points and
        // 4. use binary search to find # of ends >= points
        // 5. cnt = #s + #e -n
        Arrays.sort(starts);
        Arrays.sort(ends);
        int s =0, e = 0;
        for (int k=0; k<= points.length; k++) {
        		s = binarySearch(starts, points[k]);
        		e = ends.length - binarySearch(ends, points[k]);
        		cnt[k] = s + e - points.length;
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        System.out.println("from naive algorithm:");
        int[] cnt = naiveCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
        
        System.out.println("\nfrom fast algorithm:");
        int[] cnt2 = naiveCountSegments(starts, ends, points);
        for (int x : cnt2) {
            System.out.print(x + " ");
        }
               
    }
}

