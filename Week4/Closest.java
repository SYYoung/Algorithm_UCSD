package Week4;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

	private static final int COMPARE_X = 0;
	private static final int COMPARE_Y = 1;
	static int compareFlag;
	
    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
        		if (compareFlag == COMPARE_Y) 
        			return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        		else
        			return  o.x == x ? Long.signum(y - o.y) : Long.signum(x - o.x);
        }
        
        public String toString() {
        		return ("\t " + this.x + ",\t" + this.y);
        }
    }

    static double minimalDistance(Point[] allP) {
        double ans = Double.POSITIVE_INFINITY;
        // 1. make two copies of original points. 
        // 2. sort one of them based on x-coord and the other based on y-coord
        Point[] Px = new Point[allP.length];
        Point[] Py = new Point[allP.length];
        for (int k=0; k<allP.length; k++) {
        		Px[k] = allP[k];
        		Py[k] = allP[k];
        }
        // 3. sort both Px and Py
        compareFlag = COMPARE_X;
        Arrays.sort(Px);
        compareFlag = COMPARE_Y;
        Arrays.sort(Py);
        
        System.out.println("Px is: ");
        for (int k=0; k<allP.length; k++) 
        		System.out.println(Px[k]);
        System.out.println("Py is: ");
        for (int k=0; k<allP.length; k++) 
        		System.out.println(Py[k]);
        // 4. use recursive function closeUtil to find the shortest distance
        ans = closeUtil(Px, Py, Px.length);
        return ans;
    }

    private static double closeUtil(Point[] Px, Point[] Py, int num) {
		// 1. base case: if # of points are fewer than 4, use brute force
    		if (num <= 4) 
    			return bruteForce(Px, num);
    		
		return 0;
	}

	private static double bruteForce(Point[] px, int num) {
		double minD = Double.POSITIVE_INFINITY;
		for (int i=0; i<num; i++)
			for (int j=i+1; j<num; j++) {
				System.out.println(" i = " + i + "\t, j = " + j);
				double d = dist(px[i], px[j]);
				if (d < minD)
					minD = d;
			}
		return minD;
	}

	private static double dist(Point p1, Point p2) {
		double ans;
		ans = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
		System.out.println("distance between " + p1 + "\t" + p2 + "\t = " + ans);
		return ans;
	}

	public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Point[] allP = new Point[n];
        for (int i = 0; i < n; i++) {
        		allP[i] = new Point(nextInt(), nextInt());
        }
        System.out.println(minimalDistance(allP));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
