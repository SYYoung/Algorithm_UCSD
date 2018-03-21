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
        
        // 4. check if there is any duplicate point. if there exists, the dist will be zero.
        for (int k=0; k<Px.length-1; k++) 
        		if (Px[k] == Px[k+1])
        			return 0;
        // 5. Otherwise, use recursive function closeUtil to find the shortest distance
        ans = closeUtil(Px, Py, 0, Px.length);
        return ans;
    }

    private static double closeUtil(Point[] Px, Point[] Py, int leftMost, int num) {
		// 1. base case: if # of points are fewer than 4, use brute force
    		if (num <= 4) 
    			return bruteForce(Px, leftMost, num);
    		// Divide into 2 halves
    		int mid = num/2;
    		Point midPoint = Px[leftMost + mid];
    		System.out.println("mid point is " + midPoint);
    		// 3. get the y-sorted points from Py based on the left and right X
    		Point[] Pyl = new Point[mid+1];
    		Point[] Pyr = new Point[num-mid-1];
    		int li=0, ri = 0;
    		System.out.println("inside closeUtil: num = " + num + ", mid= " + mid);
    		for (int k=0; k<num; k++) {
    			System.out.println("Py[" + k + "] = " + Py[k]);
    			if (Py[k].x < midPoint.x)
    				Pyl[li++] = Py[k];
    			else if (Py[k].x == midPoint.x && Py[k].y == midPoint.y)
    				Pyl[li++] = Py[k];
    			else 
    				Pyr[ri++] = Py[k];
    			System.out.println("k = " + k + ", li = " + li + ", ri = " + ri);
    		}
    		// 4. then recursive perform the closeUtil on left side and right side
    		double dleft = closeUtil(Px, Pyl, leftMost, mid+1);
    		double dright = closeUtil(Px, Pyr, leftMost+mid+1, num-mid-1);
    		// 5. get the min distance between right half and left half
    		double d = Math.min(dleft, dright);
    		
    		// 6. for merge part, build the strip
    		Point[] strip = new Point[num];
    		int k = 0;
    		for (int i=0; i<num; i++) {
    			if ((Math.abs(Py[i].x - midPoint.x)) < d) {
    				strip[k] = Py[i];
    				k++;
    			}
    		}
    		// 7. now call the stripClose to find the min distance
    		double dStrip = stripClosest(strip, k, d);
    		return (Math.min(dStrip, d));
   
	}

	private static double stripClosest(Point[] strip, int k, double d) {
		// 1. compare each point with 6 neighbors and find out the min distance
		double minD = d, dSoFar;
		for (int i = 0; i<k-1; i++) {
			for (int j=i+1; (j<i+6) && (j<k) && (strip[j].y-strip[i].y < d) ; j++) {
				System.out.println("inside stripClosest: i=" + i +", j=" + j + ", k=" + k);
				dSoFar = dist(strip[i], strip[j]);
				if (dSoFar < minD)
					minD = dSoFar;
			}
		}
		return minD;
	}

	private static double bruteForce(Point[] px, int leftMost, int num) {
		double minD = Double.POSITIVE_INFINITY;
		for (int i=leftMost; i<leftMost+num; i++)
			for (int j=i+1; j<leftMost+num; j++) {
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
        // from naive algorithm
        double ans = bruteForce(allP, 0, n);
        System.out.println("From naive algorithm, the min distance = " + ans);
        System.out.print(" From divide and conquer, the min distance = ");
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
