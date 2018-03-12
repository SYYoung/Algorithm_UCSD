package Week3;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        double[] unitVal = new double[values.length];
        for (int k=0; k<values.length; k++) {
        		unitVal[k] = weights[k]==0 ? 0: values[k]/(double)weights[k];
        }
        ArrayList<Double> A = new ArrayList<Double>();
        double totalVal = 0.0, leftOver = (double)capacity;
        for (int k=0; k<values.length; k++) {
        		if (leftOver == 0.0) {
        			return totalVal;
        		}
        		double maxUnit = 0.0;
        		int maxIndex = k;
        		for (int j=k; j<values.length; j++) {
        			if (unitVal[j] > maxUnit) {
        				maxIndex = j;
        				maxUnit = unitVal[j];
        			}
        		}
        		double a = Math.min(weights[maxIndex], leftOver);
        		totalVal += a * unitVal[maxIndex];
        		//weights[maxIndex] -= a;
        		A.add(a);
        		leftOver -= a;
        		
        		// swap highIndex with k;
        		if (k != maxIndex) {
            		int tmp;
            		double tmp2;
            		tmp = values[k];
            		values[k] = values[maxIndex];
            		values[maxIndex] = tmp;
            		tmp = weights[k];
            		weights[k] = weights[maxIndex];
            		weights[maxIndex] = tmp;
            		tmp2 = unitVal[k];
            		unitVal[k] = unitVal[maxIndex];
            		unitVal[maxIndex] = tmp2;
        		}
        }

        return totalVal;
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
        double ans = getOptimalValue(capacity, values, weights);
        System.out.printf("%.4f", ans);
    }
} 
