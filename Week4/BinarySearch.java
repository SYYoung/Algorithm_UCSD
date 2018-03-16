package Week4;

import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int x) {
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
        return -1;
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }
    
    public static void stressTest() {
    		int n = new Random().nextInt(20);
    		if (n==0)
    			n = 1;
    		System.out.println(" num generated: " + n);
    		int[] a = new int[n];
    		for (int k=0; k<n; k++) {
    			a[k] = new Random().nextInt(10000);
    		}
    		System.out.println("before sorting:");
    		for (int k: a)
    			System.out.print("\t " + k);
    		System.out.println("\n. After sorting:");
    		Arrays.sort(a);
    		for (int k: a)
    			System.out.print("\t " + k);
    		
    		// now b
    		int key  = new Random().nextInt(n);
    		if (key == 0)
    			key = 1;
    		System.out.println("\n# of keys is : " + key);
    		int[] b = new int[key];
    		for (int k=0; k<key; k++) {
    			b[k] = a[(new Random().nextInt(n))];
    		}
    		b[key-1] = new Random().nextInt(10000);
    		System.out.println("\t. the keys are: ");
    		for (int k: b) {
    			System.out.print("\t " + k);
    		}
    		
    		System.out.println("\nLinear search result: ");
    		for (int k=0; k<key; k++) {
    			System.out.print(linearSearch(a, b[k]) + " ");
    		}
    		System.out.println("\n Binary search : ");
    		for (int k=0; k<key; k++) {
    			System.out.print(binarySearch(a, b[k]) + " ");
    		}
    }

    public static void main(String[] args) {
    		/*
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(linearSearch(a, b[i]) + " ");
        }
        */
    	
        stressTest();
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
