package Week3;

import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(ArrayList<Segment> segments) {
        //write your code here
		// assume that the arrays is sorted
		// R<- {}, i<-1
		// while i<=n:
		//		[l,r] <- [xi, xi+1] unit length
		//		i <- i + 1;
		//		try to find out the next leftmost point after this unit segment
		//		while i<=n and xi <= r
		//			i <- i+1
		// return R
    	
    		ArrayList<Integer> points = new ArrayList<Integer>();
    		
    		int[] results = new int[segments.size()];
    		for (int k=0; k<points.size(); k++) {
    			results[k] = points.get(k);
    		}
    		return results;
    }

    public static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public boolean isBetween(int point) {
        		if ((point >= this.start) && (point <= this.end)) {
        			return true;
        		}
        		return false;
        }

		public int compareTo(Segment seg1, Segment seg2) {
			return seg1.end < seg2.end ? -1 : seg1.end == seg2.end ? 0 : 1;
		}

		@Override
		public int compareTo(Segment seg) {
			// TODO Auto-generated method stub
			return this.end < seg.end ? -1 : this.end == seg.end ? 0: 1;
		}
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //Segment[] segments = new Segment[n];
        ArrayList<Segment> segments = new ArrayList<Segment>();
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        
        // call the sorting endpoints
        Collections.sort(segments);
        // print out the result of sorting
        for (int k=0; k<segments.size(); k++) {
        		System.out.println(segments.get(k).start + "\t" + segments.get(k).end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
