package Week3;

import java.util.ArrayList;
import java.util.Scanner;

public class Change {
    private static int getChange(int amt) {
		// A<-[0,0,...0], V<- 0
		// repeat n times:
		//		if W = 0:
		//			return (V,A)
		//		select i with wi > 0 and max vi/wi
		//		a <- min(wi, W)
		//		V <- V + a*vi/wi
		//		wi <- wi-a, A[i]<-A[i]+a, W<-W-a
		//	return (V,A)
		// however, this algorithm can be improved asympt if the array is sorted
		//  seletc i with wi>0 and max vi/wi can be removed. the array is: v1/w1 >= v2/w2>=...>=vn/Wn
        //write your code here
    		int[] dom = {10, 5, 1};
    		int leftOver = amt, totalV = 0, count = 0;
    		for (int k=0; k<dom.length; k++) {
    			if (leftOver == 0) {
    				break;
    			}
    			else {
    				int num = leftOver/dom[k];
    				count += num;
    				totalV += dom[k] * num;
    				leftOver -= dom[k] * num;
    				System.out.println("count=" + count + ", totalV = "+ totalV +
    						",  leftOver= " + leftOver);
    			}
    		}
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

