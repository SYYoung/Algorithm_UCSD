package Week3;

import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here
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

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
