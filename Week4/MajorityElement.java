package Week4;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
    		// right is the outmost index + 1
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) { 
            return a[left];
        }
        //write your code here
        int mid = (left+right-1)/2;
        int majLeft = getMajorityElement(a, left, mid+1);
        int majRight = getMajorityElement(a, mid+1, right);
        System.out.println("majLeft = " + majLeft + "\b, majRight = " 
        			 + majRight);
        if (majLeft == majRight) {
        		System.out.println("majLeft = majRight. majLeft = " + majLeft);
        		return majLeft;
        }
        if (countFreq(a, left, right, majLeft) > (right-left)/2) {
        		System.out.println("majLeft has higher freq. majLeft = " + majLeft);
        		return majLeft;
        }
        if (countFreq(a, left, right, majRight) > (right-left)/2) {
        		System.out.println("majRight has higher f. majRight= " + majRight);
        		return majRight;
        }
        System.out.println("no majorith.");
        return -1;
    }

    private static int countFreq(int[] a, int left, int right, int majorNum) {
    		int freq = 0;
		for (int k=left; k<=right-1; k++) {
			if (a[k] == majorNum)
				freq++;
		}
		return freq;
	}

	public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        
        int ans = getMajorityElement(a, 0, a.length);
        	System.out.println("\nthe majority element is : " + ans);
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

